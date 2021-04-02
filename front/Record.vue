<template>
    <div class="timeline-block">
        <el-container>
            <!-- 用户写日记的界面 -->
            <!-- 7-28 改动 -->
            <el-drawer
                :visible.sync="drawerVisible"
                :direction="direction"
                size="75%"
                class="drawer"
            >
                <el-col :span="6" class="recordSpan">
                    <div class="grid-content emptyblock"></div>
                </el-col>
                <el-col :span="12" class="recordSpan">
                    <el-container
                        style="margin-left:center; margin-bottom:500px; margin-top: -50px;"
                    >
                        <div class="inputRecordHeadText">
                            <p>今日所闻</p>
                        </div>
                    </el-container>
                    <div class="textArea" style="margin-top:-380px;">
                        <el-input
                            type="textarea"
                            placeholder="请输入您想记录的那些瞬间"
                            v-model="recordInput"
                            show-word-limit
                            :rows="8"
                        ></el-input>
                    </div>
                    <div class="btnSet" align="right" style="margin-top:10px;">
                        <el-button
                            type="success"
                            icon="el-icon-check"
                            circle
                            @click="onSubmitRecord"
                        ></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle @click="onDenyRecord"></el-button>
                    </div>
                </el-col>

                <el-col :span="5" class="recordSpan">
                    <div class="grid-content emptyblock"></div>
                </el-col>
            </el-drawer>

            <el-header class="searchBlock">
                <el-col :span="2" class="addRecordBtn">
                    <div class="grid-content addRecord">
                        <!-- 7.28修改 -->
                        <el-button
                            icon="el-icon-edit"
                            circle
                            @click="drawerVisible=!drawerVisible"
                            style="margin-left:70px; background:rgba(80, 241, 228, 0.062);"
                        ></el-button>
                    </div>
                </el-col>

                <!-- <el-col :span="7" class="recordSpan"><div class="grid-content emptyblock"></div></el-col> -->

                <el-col :span="6" class="recordSpan">
                    <div class="grid-content searchInput">
                        <el-input
                            placeholder="支持日期及内容记录搜索"
                            v-model="inputContent"
                            clearable
                            class="searchInput"
                        ></el-input>
                    </div>
                </el-col>

                <el-col :span="4" class="searchBtn">
                    <div class="grid-content searchBtn">
                        <!-- 注释部分是圆形按钮样式及实现 -->
                        <!-- <el-button icon="el-icon-search" 
                            circle    
                            @click="handleSearchBtnClick">
                        </el-button>-->
                        <a href="#" @click="handleSearchBtnClick" class="searchBtnUsingATag">Search</a>
                    </div>
                </el-col>

                <!-- 为了使搜索组件居中 -->
                <el-col :span="4" class="recordSpan">
                    <div class="grid-content emptyblock"></div>
                </el-col>
            </el-header>

            <el-main>
                <el-timeline>
                    <el-timeline-item
                        placement="top"
                        v-for="(activity, index) in searchResult"
                        :key="index"
                        :timestamp="activity.timestamp"
                        style="margin-left: 50px;"
                    >
                        <el-card>{{activity.content}}</el-card>
                    </el-timeline-item>
                </el-timeline>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // 数据通信时，只需要在activities中加入新数据即可在页面上显示

            activities: [
                {
                    content: "假如我是一只鸟，",
                    timestamp: "2018-04-15 23:21",
                },
                {
                    content: "我也就应用嘶哑的喉咙歌唱：",
                    timestamp: "2018-04-13 12:21",
                },
                {
                    content: "这被暴风雨所打击的土地，",
                    timestamp: "2018-04-12 13:21",
                },
            ],
            inputContent: "", // 用户搜索内容
            searchResult: "", // 用户搜索内容的结果展示，默认为activities内容
            drawerVisible: false,
            direction: "btt",
            recordInput: "", // 用户，输入的，日记内容，要传给后端
            recordDate: "", // 用户，提交日记的时间，要传给后端
        };
    },

    created() {
        // 重定义记录数组
        this.searchResult = this.activities;
    },

    methods: {
        // 处理点击搜索按钮
        handleSearchBtnClick() {
            var keyword = this.inputContent;
            if (keyword) {
                this.$message("以下记录包含内容: " + keyword);
                this.searchResult = this.activities.filter(function (
                    activities
                ) {
                    return Object.keys(activities).some(function (key) {
                        return (
                            String(activities[key])
                                .toLowerCase()
                                .indexOf(keyword) > -1
                        );
                    });
                });
            } else {
                // 没搜索内容，显示所有记录
                this.searchResult = this.activities;
            }
        },

        // 写日记的用户，点击了提交按钮 -> ✔ <-
        onSubmitRecord() {
            this.drawerVisible = false;
            var content = this.recordInput; // 获取输入的东西
            var time = new Date();
            var timestamp = time.toLocaleDateString();
            var hr = time.getHours();
            var min = time.getMinutes();

            timestamp = timestamp.replace(/\//g, "-");
            timestamp += " " + hr + ":" + min;

            this.recordInput = "";

            /*               传数据给后端              */

            /*     传用户id、content、timestamp回去    */

            /*      嘟嘟嘟嘟嘟嘟嘟嘟，嘀嘀嘀嘀，嘟嘟    */

            /*                 传输完毕                */

            // 我这里也会直接加到activities（或者不需要？）
            var tmp = { content, timestamp };
            this.activities.unshift(tmp);

            this.$notify({
                title: "提示",
                message: "日志已成功提交",
                duration: 3000,
                type: "success",
            });
        },

        // 写日记的用户，点击了放弃提交按钮 -> x <-
        // 后端不用管这里
        onDenyRecord() {
            this.drawerVisible = false;
            this.recordInput = ""; // 清空用户数据
            this.$notify({
                title: "提示",
                message: "已取消日志提交",
                duration: 3000,
                type: "info",
            });
        },
    },
};
</script>

<style scoped>
.timeline-block {
    /* 7.28 */
    background: url("../assets/rank_bg1.jpg");
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
}

.el-card {
    width: 80%;
    font-size: 16px;
    color: white;
    background: rgba(184, 236, 243, 0.356);
    border: none;
}

.el-timeline-item {
    color: black;
}

.timestamp {
    color: black;
}

/* .el-input {
        width: 100%;
    } */

.searchInput {
    color: white;
    background: rgba(80, 241, 228, 0.062);
    width: 100%;
}

.searchInput >>> .el-input__inner {
    color: white;
    background: transparent;
}

.empty-block {
    background: transparent;
}

.recordSpan {
    border-radius: 4px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
}

/* 自定义按钮的位置调整（与input水平） */
.searchBtn {
    margin: 5px 0px 0px 0px;
}

/* 以下为用html的标签a(超链接)制造的自定义按钮search */
.searchBtnUsingATag {
    position: relative;
    padding: 10px 30px;
    margin: 50px 0px 10px 10px;
    color: #71f7e0;
    text-decoration: none;
    font-size: 15px;
    text-transform: uppercase;
    transition: 0.5s;
    overflow: hidden;
    -webkit-box-reflect: below 1px linear-gradient(transparent, #0003);
}

.searchBtnUsingATag:hover {
    background: #71f7e0;
    color: #111;
    box-shadow: 0 0 50px #71f7e0;
    transition-delay: 0.5s;
}

.searchBtnUsingATag::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 5px;
    height: 5px;
    border-top: 2px solid #71f7e0;
    border-left: 2px solid #71f7e0;
    transition: 0.5s;
    transition-delay: 0.5s;
}

.searchBtnUsingATag:hover::before {
    width: 100%;
    height: 100%;
    transition-delay: 0s;
}

.searchBtnUsingATag::after {
    content: "";
    position: absolute;
    right: 0;
    bottom: 0;
    width: 10px;
    height: 10px;
    border-bottom: 2px solid #71f7e0;
    border-right: 2px solid #71f7e0;
    transition: 0.5s;
    transition-delay: 0.5s;
}

.searchBtnUsingATag:hover::after {
    width: 100%;
    height: 100%;
    transition-delay: 0s;
}

/*  css的穿透（>>>）功能太好用了……
        修改element drawer组件的样式方法找了很久很久很久
        今天终于找到方法了………
        7.24 zyt
    */
.drawer >>> .el-drawer__open .el-drawer.btt {
    background-color: #374d4d67;
}

.textArea >>> .el-textarea {
    font-size: 22px;
    color: black;
}

.textArea >>> .el-textarea__inner {
    color: white;
    background-color: rgba(144, 228, 243, 0.151);
    border-color: rgba(255, 192, 203, 0.582);
}

/* 渐变滚动颜色效果 7.23 */
.inputRecordHeadText {
    width: 300px;
    height: 100px;
    position: absolute;
    background-image: -webkit-linear-gradient(
        left,
        rgb(80, 241, 228),
        #66ffff 10%,
        #f7f589 20%,
        #ebf835 30%,
        #7cfff8 40%,
        #00ffff 50%,
        #7cfff8 60%,
        #ebf835 70%,
        #f7f589 80%,
        #66ffff 90%,
        rgb(80, 241, 228) 100%
    );
    -webkit-text-fill-color: transparent;
    -webkit-background-clip: text;
    -webkit-background-size: 200% 100%;
    -webkit-animation: masked-animation 4s linear infinite;
    font-size: 40px;
}

@keyframes masked-animation {
    0% {
        background-position: 0 0;
    }
    100% {
        background-position: -100% 0;
    }
}
</style>
