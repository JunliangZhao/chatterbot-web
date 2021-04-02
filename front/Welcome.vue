<template>
    <div class="welcome_contain">
        <!-- <el-carousel :interval="2000" type="card" height="400px" class="welcome0">
            <el-carousel-item v-for="item in 4" :key="item" class="welcome">
                <h3 class="medium">{{ item }}</h3>
            </el-carousel-item>
        </el-carousel> -->
        <div class="page1 page_1">
            <div class="pageContent">
                <div class="ani containerTxt fadeInUp animated">
                    <div class="page_headline">
                        <h1>给您最好的聊天体验</h1>
                    </div>
                    <div class="page_subtitle">
                        <h1>Ellie机器人期待与您相遇</h1>
                    </div>
                </div>
                <div class="Mac_phone">
                    <div class="macBg">
                        <div class="content_pc">
                            <div
                                class="content_pcBg"
                                :style="topStyle"
                                @mouseenter="visible"
                                @mouseleave="invisible"
                            ></div>
                        </div>
                    </div>
                    <div class="wxBg"></div>
                    <div class="phoneBg">
                        <div class="content_phone scrollBox">
                            <div
                                class="content_phoneBg scrollImg"
                                :style="pyStyle"
                                @mouseenter="visible1"
                                @mouseleave="invisible1"
                            ></div>
                        </div>
                    </div>
                </div>
                <div class="joinbutton">
                    <el-button type="primary" round @click="joinchat">快加入聊天</el-button>
                </div>
            </div>
        </div>
        <div class="page2 page_2">
            <div class="pageContent">
                <div class="ani containerTxt fadeInUp animated">
                    <div class="page_headline">
                        <h1>我们的网站</h1>
                    </div>
                    <div class="page_subtitle">
                        <h1>您的愉悦是我们努力的方向</h1>
                    </div>
                </div>
                <div class="webcontain">
                    <div
                        class="web1"
                        :style="webStyle1"
                        @mouseenter="webmouse1"
                        @mouseleave="webinmouse1"
                    ></div>
                    <div
                        class="web2"
                        :style="webStyle2"
                        @mouseenter="webmouse2"
                        @mouseleave="webinmouse2"
                    ></div>
                    <div
                        class="web3"
                        :style="webStyle3"
                        @mouseenter="webmouse3"
                        @mouseleave="webinmouse3"
                    ></div>
                    <div class="webde1" v-show="show1">成长的记录</div>
                    <div class="webde2" v-show="show2">聊得火热</div>
                    <div class="webde3" v-show="show3">激烈的比拼</div>
                </div>
                <div class="joinusbutton">
                    <el-button type="primary" round @click="joinus">了解我们</el-button>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: "Welcome.vue",
    data() {
        return {
            topStyle: {
                top: "0px",
            },
            pyStyle: {
                left: "0rem",
            },
            webStyle1: {
                height: "13.2rem",
                top: "5rem",
            },
            webStyle2: {
                height: "13.2rem",

                top: "5rem",
            },
            webStyle3: {
                height: "13.2rem",

                top: "5rem",
            },
            show1: false,
            show2: false,
            show3: false,
        };
    },

    methods: {
        visible: function () {
            this.topStyle.top = "-150px";
        },
        invisible: function () {
            this.topStyle.top = "-0px";
        },
        visible1: function () {
            this.pyStyle.left = "-8rem";
        },
        invisible1: function () {
            this.pyStyle.left = "0rem";
        },
        joinchat: function () {
            this.$router.push("/model");
        },
        webmouse1: function () {
            this.webStyle1.height = "15.2rem";
            this.webStyle1.top = "4rem";
            this.show1 = true;
        },
        webinmouse1: function () {
            this.webStyle1.height = "13.2rem";
            this.webStyle1.top = "5rem";
            this.show1 = false;
        },
        webmouse2: function () {
            this.webStyle2.height = "15.2rem";
            this.webStyle2.top = "4rem";
            this.show2 = true;
        },
        webinmouse2: function () {
            this.webStyle2.height = "13.2rem";

            this.webStyle2.top = "5rem";
            this.show2 = false;
        },
        webmouse3: function () {
            this.webStyle3.height = "15.2rem";

            this.webStyle3.top = "4rem";
            this.show3 = true;
        },
        webinmouse3: function () {
            this.webStyle3.height = "13.2rem";
            this.webStyle3.top = "5rem";
            this.show3 = false;
        },
        joinus: function () {
            this.$router.push("/about");
        },
    },
};
</script>
<style lang = "less" scoped>
.welcome_contain {
    width: 100%;
    height: 100%;
}

.page1 {
    position: relative;
    width: 100%;
    height: 100%;
}

.page_1 {
    width: 100%;
    height: 100%;
    position: relative;
    background: url("../assets/welcome2bg.jpg") center 0 no-repeat;
    background-size: cover;
}
.page2 {
    position: relative;
    width: 100%;
    height: 100%;
}

.page_2 {
    width: 100%;
    height: 100%;
    position: relative;
    background: white;
    background-size: cover;
}
.pageContent {
    height: 100%;
    width: 86.664rem;
    margin: 0 auto;
}
.containerTxt {
    position: relative;
    width: 100%;
    height: auto;
    top: 7%;
    right: 0;
    left: 0;
    margin: auto;
    color: #3b3b3b;
    background-image: none;
}
.page_headline {
    font-size: 2.4rem;
    margin: 0 auto;
    text-align: center;
}
.page_subtitle {
    font-size: 1.2rem;
    margin: 0 auto;
    margin-top: 0.64rem;
    text-align: center;
}

.Mac_phone {
    width: 54.6rem;
    height: 31.68rem;
    position: absolute;
    top: 24%;
    left: 0;
    right: 0;
    margin: auto;
}
.macBg {
    width: 41.12rem;
    height: 30.2rem;
    background-image: url("../assets/computer.png");
    background-size: 41.12rem;
    position: relative;
    margin-left: 6.24rem;
    background-repeat: no-repeat;
}
.content_pc {
    position: relative;
    top: 0.72rem;
    width: 38.72rem;
    height: 20.96rem;
    margin: 0 auto;
    overflow: hidden;
}
.content_pcBg {
    width: 38.72rem;
    height: 114.88rem;
    background-image: url("../assets/content_pc.png");
    background-size: 38.72rem;
    position: relative;
}

.wxBg {
    width: 14.24rem;
    height: 12.4rem;
    background-image: url(//jz.faisys.com/image/baiduHome/proVersion/proVer5/WeChat.png?v=201706221635);
    background-size: 14.24rem;
    position: absolute;
    right: 0;
    bottom: 0;
    background-repeat: no-repeat;
}
.phoneBg {
    width: 10.56rem;
    height: 17.76rem;
    background-image: url(//jz.faisys.com/image/baiduHome/proVersion/proVer5/iphone.png?v=201706221635);
    background-size: 10.56rem;
    position: absolute;
    left: 0;
    bottom: 0;
    background-repeat: no-repeat;
}
.content_phone {
    position: relative;
    left: 0.39rem;
    top: 1.8rem;
    width: 8.2rem;
    height: 13.8rem;
    overflow: hidden;
}
.content_phoneBg {
    width: 16.5rem;
    height: 14rem;
    background-image: url("../assets/content_phone.jpg");
    background-size: 16.16rem;
    position: relative;
    background-repeat: no-repeat;
}
.joinbutton {
    position: absolute;
    bottom: 4.28rem;
    left: 0;
    right: 0;
    margin: auto;
    width: 12.88rem;
    height: 3.064rem;
}

.webcontain {
    width: 81.6rem;
    height: auto;
    top: 25.4%;
    position: absolute;
    left: 0;
    right: 0;
    margin: auto;
}

.web1 {
    float: left;
    width: 22.5rem;
    left: 0rem;
    margin-bottom: 3.6rem;
    position: relative;
    background-image: url("../assets/web1.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
}
.web2 {
    float: left;
    width: 22.5rem;
    left: 10rem;
    margin-bottom: 3.6rem;
    position: relative;
    background-image: url("../assets/web2.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
}
.web3 {
    float: left;
    width: 22.5rem;
    left: 20rem;
    margin-bottom: 3.6rem;
    position: relative;
    background-image: url("../assets/web3.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
}
.webde1 {
    float: left;
    width: 22.5rem;
    left: -20rem;
    margin-bottom: 3.6rem;
    position: relative;
    top: 4rem;
    font-size: 50px;
    height: 5rem;
}
.webde2 {
    float: left;
    width: 22.5rem;
    left: -10rem;
    margin-bottom: 3.6rem;
    position: relative;
    top: 4rem;
    font-size: 50px;
    height: 5rem;
}
.webde3 {
    float: left;
    width: 22.5rem;
    left: 70rem;
    margin-bottom: 3.6rem;
    position: relative;
    top: 4rem;
    font-size: 50px;
    height: 5rem;
}

.joinusbutton {
    position: absolute;
    bottom: 4.28rem;
    left: 0;
    right: 0;
    margin: auto;
    width: 12.88rem;
    height: 3.064rem;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}
/* 走马灯项背景 */
.el-carousel__item:nth-child(4n) {
    background: url("../assets/rank_bg1.jpg");
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
}
.el-carousel__item:nth-child(4n + 1) {
    background: url("../assets/rank_bg2.jpg");
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
}
.el-carousel__item:nth-child(4n + 2) {
    background: url("../assets/rank_bg3.jpg");
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
}
.el-carousel__item:nth-child(4n + 3) {
    background: url("../assets/rank_bg4.jpg");
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
}
/* 排行列表项颜色 */
.el-table .warning-row {
    background: #fdffff;
}
</style>