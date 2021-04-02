<template>
    <div class="chat">
        <el-col :span="5" align="center">
            <el-button
                class="infoBtn"
                @click="showinfo"
                style="width: 100%; background:transparent; color:white; border-color:rgba(119, 136, 153, 0.1);"
            >
                <font v-if="infoshow">隐藏Bot信息</font>
                <font v-if="!infoshow">显示Bot信息</font>
            </el-button>

            <transition name="el-zoom-in-top">
                <el-main class="set-c" v-show="infoshow">
                    <el-form class="set-form" ref="botform" :model="botform">
                        <!-- <font size="3" style="margin-bottom:20px; color:white;">名字</font> -->
                        <el-form-item label="名字" class="infoItem">
                            <el-input v-model="botform.botName" class="infoInput"></el-input>
                        </el-form-item>
                        <el-form-item label="主人名字" class="infoItem-masterName">
                            <el-input
                                v-model="botform.botMaster"
                                :disabled="true"
                                class="infoInput"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="性别:" class="infoItem-btn">
                            <el-radio-group v-model="botform.botSex" style="margin-left:10px;">
                                <el-radio label="MALE" class="radio">男</el-radio>
                                <el-radio label="FEMALE" class="radio">女</el-radio>
                                <el-radio label="NULL" class="radio">保密</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="性取向:" class="infoItem-btn">
                            <el-radio-group v-model="botform.botSexualOri">
                                <el-radio label="MALE" class="radio">男</el-radio>
                                <el-radio label="FEMALE" class="radio">女</el-radio>
                                <el-radio label="NULL" class="radio">保密</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="分数" class="infoItem">
                            <el-input v-model="botform.botScore" :disabled="true" class="infoInput"></el-input>
                        </el-form-item>
                        <el-form-item label="备注" class="infoItem">
                            <el-input v-model="botform.botNote" class="infoInput"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="save()">保存</el-button>
                            <el-button @click="cancle()">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </transition>
        </el-col>

        <el-col :span="13" class="chat-c">
            <el-container direction="vertical">
                <el-main class="show-c">
                    <div class="chatBorder">
                        <div style="margin-left: 30px; margin-top: 15px;">
                            <el-row
                                v-for="tag in mes"
                                :text="tag"
                                :key="tag.recordDate+tag.recordTime"
                            >
                                <div v-if="tag.messageType==true">
                                    <el-row>
                                        <div align="center" style="padding: 15px 0;">
                                            <el-tag
                                                type="info"
                                                size="mini"
                                                v-text="tag.recordDate + ' '+tag.recordTime"
                                            ></el-tag>
                                        </div>
                                    </el-row>
                                    <el-row>
                                        <el-col :span="21">
                                            <div class="chatPop2" style="white-space: pre-wrap;">
                                                <span
                                                    style="line-height: 23px;"
                                                    v-text="tag.record"
                                                ></span>
                                            </div>
                                        </el-col>
                                        <el-col :span="2">
                                            <el-avatar
                                                shape="square"
                                                :src="circleUrl"
                                                style="width: 35px;height: 35px;"
                                            ></el-avatar>
                                        </el-col>
                                    </el-row>
                                </div>

                                <div v-else>
                                    <el-col :span="2">
                                        <el-avatar
                                            shape="square"
                                            :src="circleUrl"
                                            style="width: 35px;height: 35px;"
                                        ></el-avatar>
                                    </el-col>
                                    <el-col :span="21">
                                        <div class="chatPop1" style="white-space: pre-wrap;">
                                            <span style="line-height: 23px;" v-text="tag.record"></span>
                                        </div>
                                    </el-col>
                                </div>
                            </el-row>
                        </div>
                    </div>
                </el-main>

                <el-main class="input-c">
                    <el-col :span="20">
                        <el-input
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            v-model="inputText"
                            maxlength="50"
                            show-word-limit
                        ></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button class="sendBut" size="medium" @click="send()">发送</el-button>
                    </el-col>
                </el-main>
            </el-container>
        </el-col>
        <el-col :span="5" class="model-c"></el-col>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "app",

    data() {
        return {
            infoshow: false,
            set_corlor: true,
            inputText: "你好！",
            circleUrl:
                "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            squareUrl:
                "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            sizeList: ["large", "medium", "small"],
            mes: [],

            botform: {
                botAccount: "",
                botMaster: "9527",
                botName: "Ellie",
                botSex: "女",
                botSexualOri: "女",
                botScore: "0",
                botNote: "001",
            },
            bot: window.L2Dwidget, // 新加的机器人
        };
    },
    created() {
        //创建页面的时候获取机器人信息
        this.botform.botAccount = JSON.parse(
            window.sessionStorage.getItem("loginForm")
        ).userAccount;
        let that = this;
        this.$http
            .get("/botInfo/" + this.botform.botAccount)
            .then(function (response) {
                // handle success
                console.log(response);
                that.botform = response.data.data;
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

        //获取聊天记录
        this.$http
            .get("/record" + "/" + this.botform.botAccount)
            .then(function (response) {
                // handle success
                // console.log(response);
                that.mes = response.data.data;
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

        // 画机器人
        setTimeout(() => {
            this.bot.init({
                pluginRootPath: "live2dw/",
                pluginJsPath: "lib/",
                pluginModelPath: "live2d-widget-model-nico/assets/",
                tagMode: false,
                debug: false,
                model: {
                    jsonPath:
                        "/live2dw/live2d-widget-model-hibiki/assets/hibiki.model.json",
                },
                // hibiki就是角色,想换个角色,换这个就可以了
                display: {
                    position: "right",
                    width: 300,
                    height: 750,
                },
                mobile: { show: true },
                log: false,
            });
        }, 300);
    },
    methods: {
        async showinfo() {
            this.infoshow = !this.infoshow;
            // let that = this;
            // this.botform.botAccount = JSON.parse(
            //   window.sessionStorage.getItem("loginForm")
            // ).userAccount;
        },
        async send() {
            var aDate = new Date();
            this.mes.push({
                userAccount: this.botform.botAccount,
                record: this.inputText,
                messageType: true,
                recordDate:
                    aDate.getFullYear() +
                    "-" +
                    (aDate.getMonth() + 1) +
                    "-" +
                    aDate.getDate(),
                recordTime:
                    aDate.getHours() +
                    ":" +
                    aDate.getMinutes() +
                    ":" +
                    aDate.getSeconds(),
            });
            //新增的用户对话返回给后端
            let that = this;
            await this.$http
                .post("/record", that.mes[that.mes.length - 1])
                .then(function (response) {
                    // console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });

            this.inputText = "";
            this.reply();
        },
        async reply() {
            let B_mes = new FormData();

            B_mes.append("usermes", this.mes[this.mes.length - 1].record);
            if (this.mes.length >= 2)
                B_mes.append("botmes", this.mes[this.mes.length - 2].record);
            else B_mes.append("botmes", "不懂");

            axios
                .post("http://localhost:8080/biz/" + "mesdata.json", B_mes, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                })
                .then((response) => {
                    //console.log(response);
                    var reply = response["data"]["reply"];
                    var bDate = new Date();
                    console.log(reply);
                    this.mes.push({
                        userAccount: this.botform.botAccount,
                        record: reply,
                        messageType: false,
                        recordDate:
                            bDate.getFullYear() +
                            "-" +
                            (bDate.getMonth() + 1) +
                            "-" +
                            bDate.getDate(),
                        recordTime:
                            bDate.getHours() +
                            ":" +
                            bDate.getMinutes() +
                            ":" +
                            bDate.getSeconds(),
                    });
                    //返回得到的机器人聊天消息给后端
                    let that = this;
                    this.$http
                        .post("/record", that.mes[that.mes.length - 1])
                        .then(function (response) {
                            console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                })
                .catch((err) => {
                    console.log(err);
                    this.$message.error("网络故障，请求失败！");
                });
        },

        async save() {
            this.$confirm("此操作将保存当前机器人属性, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    //传回数据库
                    let that = this;
                    // this.botform.botAccount = JSON.parse(
                    //   window.sessionStorage.getItem("loginForm")
                    // ).userAccount;
                    this.$http
                        .put("/botInfo", that.botform)
                        .then(function (response) {
                            console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                    this.$message({
                        type: "success",
                        message: "保存成功!",
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消保存",
                    });
                });
        },
        //
        cancle() {
            //还原数据
            let that = this;
            this.$http
                .get("/botInfo/" + this.botform.botAccount)
                .then(function (response) {
                    // handle success
                    console.log(response);
                    that.botform = response.data.data;
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                })
                .then(function () {
                    // always executed
                });

            this.$message({
                type: "info",
                message: "已取消修改，还原为初始值",
            });
            //reload
        },
    },
};
</script>

<style scoped>
.chat {
    position: absolute;

    /* top: 50%; */
    width: 100%;
    height: 100%;
    /* margin: -190px 0 0 -175px; */
    border-radius: 5px;
    background-image: url(../assets/modelBG.jpg);
    background-size: 140%;
    overflow: hidden;
}

.set-c {
    background-color: rgba(204, 253, 237, 0.171);
    color: rgb(255, 255, 255);
    text-align: center;
    height: 100%;
}

.chat-c {
    background-color: rgba(0, 0, 0, 0.3);
    color: rgb(0, 0, 0);
    text-align: center;
    height: 100%;
}

.model-c {
    background-color: rgba(0, 0, 0, 0.3);
}
.show-c {
    background-color: transparent;
    border: 1px solid white;
    color: rgb(0, 0, 0);
    text-align: center;
    height: 600px;
}
.input-c {
    background-color: rgba(235, 29, 29, 0.3);
    /* border: 1px solid; */
    color: rgb(0, 0, 0);
    text-align: center;
}

.sendBut {
    margin-top: 5px;
    text-align: center;
}

/* zyt 7.31 update */
.chatBorder {
    background-color: rgba(119, 136, 153, 0.274);
    border: rgba(119, 136, 153, 0.274);
    /* background-color: #f5f5f5; */
    /* border: 2px solid ; */
    padding: 0;
    display: block;
}

.chatPop1 :hover {
    background-color: #fafafa;
}
.chatPop1 span {
    text-align: left;
    background-color: #fff;
    padding: 5px 8px;
    display: inline-block;
    border-radius: 10px;
    margin: 0px 0px 10px 10px;
    position: relative;
    border: 1px solid #e3e3e3;
    max-width: 290px;
    float: left;

    /* 自动换行 */
    word-wrap: break-word;
}
.chatPop1::after {
    content: "";

    border: 12px solid #ffffff00;
    border-right: 12px solid #fff;
    position: absolute;
    top: 8%;
    left: 7%;
}

.chatPop2 :hover {
    background-color: #2683f5;
}
.chatPop2 span {
    text-align: left;
    background-color: #2683f5;
    padding: 5px 8px;
    display: inline-block;
    border-radius: 10px;
    margin: 0px 15px 10px 10px;
    position: relative;
    border: 1px solid #2683f5;
    max-width: 290px;
    float: right;
    color: #fff;

    /* 自动换行 */
    word-wrap: break-word;
}

.chatPop2::after {
    content: "";
    border: 11px solid #00000000;
    border-right: 11px solid #2683f5;
    position: absolute;
    top: 8%;
    right: 12%;
    transform: rotateY(180deg);
}

/* zyt 7.31 update */
.infoInput >>> .el-input__inner {
    background-color: rgba(119, 136, 153, 0.274);
    border-color: rgba(119, 136, 153, 0.664);
    color: white;
}

.infoInput.el-input.is-disabled >>> .el-input__inner {
    background-color: rgba(119, 136, 153, 0.274);
    border-color: rgba(119, 136, 153, 0.664);
    color: white;
}

.infoItem >>> .el-form-item__label {
    color: white;
    /* why shouldn't this be 50% ?*/
    /* only god knows */
    margin-left: 45%;
}

.infoItem-masterName >>> .el-form-item__label {
    color: white;
    /* this has to be 40% */
    /* or it's not in the center */
    /* why shouldn't this be 50% ?*/
    /* only god knows */
    margin-left: 40%;
}

.infoItem-btn >>> .el-form-item__label {
    color: white;
}

.radio >>> .el-radio__label {
    color: white;
}

.radio >>> .el-radio__inner {
    background-color: rgba(119, 136, 153, 0.274);
    border-color: rgba(119, 136, 153, 0.664);
}

.el-radio.radio.is-checked >>> .el-radio__label {
    color: white;
}

.el-radio.radio.is-checked >>> .el-radio__inner {
    background-color: rgba(119, 136, 153, 0.274);
    border-color: rgba(119, 136, 153, 0.664);
}

.el-main.show-c {
    padding: 0;
    border: transparent;
}
</style>