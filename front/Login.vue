<template>
    <div id="login">
        <!-- background == bgd -->
        <div id="bgd">
            <canvas id="myCanvas" :width="width" :height="height"></canvas>
        </div>
        <div id="loginBox">
            <center style="text-shadow: 0 0 0.2em #F87, 0 0 0.2em #F87; font:30;">
                <font size="5">登录</font>
            </center>
            <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="0px">
                <el-form-item label prop="userName" style="margin-top:40px;">
                    <el-row>
                        <el-col :span="2">
                            <i class="el-icon-user"></i>
                        </el-col>
                        <el-col :span="22">
                            <el-input
                                class="inps"
                                placeholder="电子邮箱"
                                v-model="loginForm.userAccount"
                            ></el-input>
                        </el-col>
                    </el-row>
                </el-form-item>

                <el-form-item label prop="passWord">
                    <el-row>
                        <el-col :span="2">
                            <i class="el-icon-lock"></i>
                        </el-col>
                        <el-col :span="22">
                            <el-input class="inps" placeholder="密码" v-model="loginForm.userPwd"></el-input>
                        </el-col>
                    </el-row>
                </el-form-item>

                <el-form-item style="margin-top:35px; margin-left:20px;">
                    <el-button type="primary" round class="submitBtn" @click="login">进入赛博朋克</el-button>
                </el-form-item>
                <el-form-item class="signin" style="margin-left:55px; margin-top:0px;">
                    <font size="2">没有账号？</font>
                    <el-link
                        :underline="false"
                        @click="enterSigninDialog"
                        style="margin-bottom:3px;"
                    >
                        <font
                            size="2"
                            style="text-shadow: 0 0 0.2em #F87, 0 0 0.2em #F87; color:white;"
                        >注册</font>
                    </el-link>
                    <font size="2">一个</font>
                </el-form-item>
            </el-form>
        </div>

        <div class="signinDialog" style="background:red;">
            <el-dialog
                title="成为赛博朋克人"
                :visible.sync="signinVisible"
                width="30%"
                custom-class="signinDialog"
            >
                <el-form label-width="10px">
                    <el-form-item label>
                        <i class="el-icon-user"></i>
                        <el-input
                            v-model="signInFrom.signInAccount"
                            placeholder="请输入电子邮箱"
                            style="width:320px; margin-left: 20px;"
                        ></el-input>
                    </el-form-item>

                    <el-form-item label>
                        <i class="el-icon-lock"></i>
                        <el-input
                            v-model="signInFrom.signInPwd"
                            placeholder="请输入6-10位密码，应含字母和数字"
                            style="width :320px; margin-left: 20px;"
                            show-password
                        ></el-input>
                    </el-form-item>

                    <el-form-item label>
                        <i class="el-icon-unlock"></i>
                        <el-input
                            v-model="signInFrom.signInCheckPwd"
                            placeholder="请再次输入密码"
                            style="width :320px; margin-left: 20px;"
                            show-password
                        ></el-input>
                    </el-form-item>
                </el-form>

                <span slot="footer" class="dialog-footer">
                    <el-button
                        type="success"
                        icon="el-icon-check"
                        circle
                        @click="signin"
                        style="margin-left:20px;"
                    ></el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            canvas: null,
            context: null,
            stars: [],
            shadowColorList: [
                "#39f",
                "#ec5707",
                "#b031d4",
                "#22e6c7",
                "#92d819",
                "#14d7f1",
                "#e23c66",
            ],
            directionList: ["leftTop", "leftBottom", "rightTop", "rightBottom"],
            speed: 50,
            last_star_created_time: new Date(),
            Ball: class Ball {
                constructor(radius) {
                    this.x = 0;
                    this.y = 0;
                    this.radius = radius;
                    this.color = "";
                    this.shadowColor = "";
                    this.direction = "";
                }

                draw(context) {
                    context.save();
                    context.translate(this.x, this.y);
                    context.lineWidth = this.lineWidth;
                    var my_gradient = context.createLinearGradient(0, 0, 0, 8);
                    my_gradient.addColorStop(0, this.color);
                    my_gradient.addColorStop(1, this.shadowColor);
                    context.fillStyle = my_gradient;
                    context.beginPath();
                    context.arc(0, 0, this.radius, 0, Math.PI * 2, true);
                    context.closePath();

                    context.shadowColor = this.shadowColor;
                    context.shadowOffsetX = 0;
                    context.shadowOffsetY = 0;
                    context.shadowBlur = 10;

                    context.fill();
                    context.restore();
                }
            },
            width: window.innerWidth,
            height: window.innerHeight,

            signinVisible: false, // 注册框是否显示

            /* ------------------------- 数据改动-----------libofan---------------  */
            //注册机器人绑定的表单
            botform: {
                botAccount: "",
                botMaster: "",
                botName: "Ellie",
                botSex: "",
                botSexualOri: "",
                botScore: "0",
                botNote: "little cyberpuker",
            },
            //注册时绑定的表单
            signInFrom: {
                signInName: "",
                signInAccount: "",
                signInPwd: "",
                signInCheckPwd: "",
                signInType: "",
                signInSex: "",
                signInNote: "",
                signInAvailable: "",
                signInAge: "",
            },
            //登陆时绑定的表单
            loginForm: {
                userName: "",
                userAccount: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },
            //提交的的表单
            postForm: {
                userName: "cyberpunker",
                userAccount: "",
                userPwd: "",
                userType: "false",
                userSex: "保密",
                userNote: "feel good",
                userAvailable: "true",
                userAge: "18",
            },
            /* ------------------------- 数据改动-----------libofan---------------  */
            //验证对象
            loginRules: {
                //校验用户名
                username: [
                    { required: true, message: "请输入邮箱", trigger: "blur" },
                    {
                        min: 5,
                        max: 12,
                        message: "长度在 5 到 12 个字符",
                        trigger: "blur",
                    },
                ],
                //校验密码
                password: [
                    {
                        required: true,
                        message: "请输入用户密码",
                        trigger: "blur",
                    },
                    {
                        min: 6,
                        max: 10,
                        message: "长度在 6 到 10 个字符",
                        trigger: "blur",
                    },
                ],
            },
        };
    },

    methods: {
        // 点击登录按钮,传数据
        login() {
            //验证校验规则
            let that = this;
            let data = this.loginForm;
            let email = data.userAccount;
            let password = data.userPwd;
            this.$refs.loginFormRef.validate(async (valid) => {
                await this.$http
                    .get("/login" + "/" + email + "/" + password)
                    .then(function (response) {
                        // console.log(response);
                        // let res = JSON.parse(response.request.response);
                        // console.log(res);
                        if (response.data.code == 200) {
                            that.$message.success("登陆成功"); //信息提示
                            that.$router.push({ path: "/home" }); //路由
                            window.sessionStorage.setItem(
                                "loginForm",
                                JSON.stringify(response.data.data)
                            ); //后台返回用户数据存储在session中，用于下次发起请求时携带
                            // console.log(JSON.parse(window.sessionStorage.getItem('loginForm')));
                        } else that.$message.error("用户不存在或密码错误");
                    })
                    .catch(function (error) {
                        console.log(error);
                    })
                    .then(function () {
                        // always executed
                    });
            });
        },

        /* ------------------------- 没有动过你的数据------nobody--------------------  */

        async signin() {
            // 用户点击了注册按钮，就是注册界面那个勾 -> ✔ <-

            // 后端要干什么呢？

            // 我先检测用户输入的数据是否格式正确

            // 传数据在检测下面下写吧

            // 检测开始……
            var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var pwdLetterReg = /[a-zA-Z]+/;
            var pwdNumReg = /[0-9]+/;

            if (!emailReg.test(this.signInFrom.signInAccount)) {
                this.$notify({
                    title: "提示",
                    message: "邮箱格式不正确",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.signInFrom.signInAccount = "";
                return;
            }

            if (this.signInFrom.signInPwd != this.signInFrom.signInCheckPwd) {
                this.$notify({
                    title: "提示",
                    message: "两次输入的密码不一致",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.signInFrom.signInPwd = "";
                this.signInFrom.signInCheckPwd = "";
                return;
            }

            if (
                this.signInFrom.signInPwd.length < 6 ||
                this.signInFrom.signInPwd.length > 10
            ) {
                this.$notify({
                    title: "提示",
                    message: "密码长度应在6~10位", // 我是根据你原来的写的，你上面数据的提示就是密码6~10位
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.signInFrom.signInPwd = "";
                this.signInFrom.signInCheckPwd = "";
                return;
            }

            if (!pwdLetterReg.test(this.signInFrom.signInPwd)) {
                this.$notify({
                    title: "提示",
                    message: "密码至少应有1个字母",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.signInFrom.signInPwd = "";
                this.signInFrom.signInCheckPwd = "";
                return;
            }

            if (!pwdNumReg.test(this.signInFrom.signInPwd)) {
                this.$notify({
                    title: "提示",
                    message: "密码至少应有1个数字",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.signInFrom.signInPwd = "";
                this.signInFrom.signInCheckPwd = "";
                return;
            }

            // 到这，检测结束

            // 传数据,新增校验，不能重复注册
            let that = this;
            let that2 = this;
            this.postForm.userPwd = this.signInFrom.signInPwd;
            this.postForm.userAccount = this.signInFrom.signInAccount;
            let data = this.postForm;
            console.log(data);
            //调用axios的post方法
            let url = "login";
            await this.$http
                .post(url, data)
                .then((res) => {
                    console.log(res);
                    if (res.data.code == 200) {
                        that.$message.success("注册成功");
                        //用户注册后也要注册一个默认的机器人
                        that.botform.botAccount = data.userAccount;
                        that.botform.botMaster = data.userName;
                        that.$http
                            .post("/login/botIncrement", that2.botform)
                            .then(function (response) {
                                console.log(response);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    } else if (res.data.code == 401)
                        that.$message.error("该邮箱已被注册");
                    else that.$message.error("注册失败");
                })
                .catch((err) => {
                    console.log(err);
                });

            this.signinVisible = false; // 关窗口

            /*这个成功的提示我删掉了，因为我前边根据后端的结果已经返回不同的提示了---libofan*/

            // this.$notify({
            //   // 提示
            //   title: "提示",
            //   message: "注册成功",
            //   duration: 3000,
            //   type: "success",
            // });
        },

        //重复动画
        drawFrame() {
            let animation = requestAnimationFrame(this.drawFrame);
            this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
            this.createStar(false);
            this.stars.forEach(this.moveStar);
        },

        //展示所有的星星
        createStar(params) {
            let now = new Date();
            if (params) {
                //初始化星星
                for (var i = 0; i < 50; i++) {
                    const radius = Math.random() * 3 + 2;
                    let star = new this.Ball(radius);
                    star.x = Math.random() * this.canvas.width + 1;
                    star.y = Math.random() * this.canvas.height + 1;
                    star.color = "#ffffff";
                    star.shadowColor = this.shadowColorList[
                        Math.floor(Math.random() * this.shadowColorList.length)
                    ];
                    star.direction = this.directionList[
                        Math.floor(Math.random() * this.directionList.length)
                    ];
                    this.stars.push(star);
                }
            } else if (!params && now - this.last_star_created_time > 3000) {
                //每隔3秒重绘修改颜色其中30个球阴影颜色
                for (var i = 0; i < 30; i++) {
                    this.stars[i].shadowColor = this.shadowColorList[
                        Math.floor(Math.random() * this.shadowColorList.length)
                    ];
                }
                this.last_star_created_time = now;
            }
        },

        //移动
        moveStar(star, index) {
            if (star.y - this.canvas.height > 0) {
                //触底
                if (Math.floor(Math.random() * 2) === 1) {
                    star.direction = "leftTop";
                } else {
                    star.direction = "rightTop";
                }
            } else if (star.y < 2) {
                //触顶
                if (Math.floor(Math.random() * 2) === 1) {
                    star.direction = "rightBottom";
                } else {
                    star.direction = "leftBottom";
                }
            } else if (star.x < 2) {
                //左边
                if (Math.floor(Math.random() * 2) === 1) {
                    star.direction = "rightTop";
                } else {
                    star.direction = "rightBottom";
                }
            } else if (star.x - this.canvas.width > 0) {
                //右边
                if (Math.floor(Math.random() * 2) === 1) {
                    star.direction = "leftBottom";
                } else {
                    star.direction = "leftTop";
                }
            }
            if (star.direction === "leftTop") {
                star.y -= star.radius / this.speed;
                star.x -= star.radius / this.speed;
            } else if (star.direction === "rightBottom") {
                star.y += star.radius / this.speed;
                star.x += star.radius / this.speed;
            } else if (star.direction === "leftBottom") {
                star.y += star.radius / this.speed;
                star.x -= star.radius / this.speed;
            } else if (star.direction === "rightTop") {
                star.y -= star.radius / this.speed;
                star.x += star.radius / this.speed;
            }
            star.draw(this.context);
        },

        enterSigninDialog() {
            this.signinVisible = true;
        },
    },

    mounted() {
        this.canvas = document.getElementById("myCanvas");
        this.context = this.canvas.getContext("2d");

        this.createStar(true);
        this.drawFrame();
    },
};
</script>

<style lang='less'scoped>
#login {
    // width: 100vw;
    width: 100%;
    padding: 0;
    margin: 0;
    // height: 100vh;
    height: 100%;
    font-size: 16px;
    background-repeat: no-repeat;
    background-position: left top;
    background-color: #242645;
    color: #fff;
    font-family: "Source Sans Pro";
    background-size: 100% 100%;
    background-image: url("../assets/login_background.jpg");

    position: relative;

    #bgd {
        height: 100vh;
        width: 100vw;
        overflow: hidden;
    }

    #loginBox {
        width: 240px;
        height: 280px;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
        padding: 50px 40px 40px 40px;
        box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
        opacity: 1;
        background: linear-gradient(
            230deg,
            rgba(53, 57, 74, 0) 0%,
            rgb(0, 0, 0) 100%
        );

        /deep/ .inps input {
            border: none;
            color: #fff;
            background-color: transparent;
            font-size: 12px;
        }

        .submitBtn {
            background-color: transparent;
            color: pink;
            border-color: pink;
            width: 200px;
        }

        .submitBtn:hover {
            background-color: rgba(243, 120, 120, 0.589);
            color: rgb(255, 255, 255);
        }

        .iconfont {
            color: #fff;
        }

        // .signinDialog > el-dialog__top {
        //     background: red;
        // }

        // .signinDialog {
        //     background: red;
        //     background-color:rgba(0, 0, 0, 0.041);
        // }

        // .el-dialog{
        //      background-color:rgba(0, 0, 0, 0.041);
        // }
    }
}
</style>

<style scoped>

.signinDialog >>> .el-dialog {
    background-color: rgba(252, 216, 222, 0.836);
}

.signinInput >>> .el-input {
    background-color:rgba(243, 191, 200, 0.466);
}

.signinDialog >>> .el-dialog__title {
    color: black;
}

.signinDialog >>> .el-input__inner{
    color: black;
    background-color:rgba(255, 221, 227, 0.849);
    border-color:rgba(243, 138, 156, 0.26);
}
</style>