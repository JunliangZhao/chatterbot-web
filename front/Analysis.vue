<template>
    <div
        id="app"
        class="wrapper"
        v-cloak
        v-bind:class="{'is-previous': isPreviousSlide, 'first-load': isFirstLoad}"
        style="max-height: 95%;"
    >
        <!-- 💖slide-wrapper:一共三张幻灯片,外包装 -->
        <!-- 💖slide-bg-text:背景那个灰嘛嘛的文字 -->
        <!-- 💖slide-content:主要内容 -->
        <!-- 💖slide-side-text:左边的,竖着🙃显示的,那些字-->
        <!-- 💖controls-container:下方的,切换幻灯片的,按钮🔳 -->
        <!-- 💖pagination-container:右边的,被沙雕机器人挡住的,幻灯片序号表示 -->
        <!-- 最终版本，机器人不会在这个界面出现 -->
        <!-- nobody 7.25 v1.0 -->

        <!-- 💖现在机器人已经不在此页面显示 zyt 7.31 v2.0 -->

        <div
            class="slide-wrapper"
            v-for="(slide, index) in slides"
            v-bind:class="{ active: index === currentSlide }"
            v-bind:style="{ 'z-index': (slides.length - index) }"
        >
            <!-- ❗❗❗上面^^^^^^^^若报错❗❗❗，进入File-> preference -> settings，修改 "vetur.validation.template"为false，我的默认为true😀-->
            <div class="slide-inner" style="margin-left:-80px;">
                <div class="slide-bg-text">
                    <p>{{ slide.headlineFirstLine }}</p>
                    <p>{{ slide.headlineSecondLine }}</p>
                </div>

                <div class="slide-content">
                    <!-- 🐷第一张幻灯片:聊天统计表，大数据分析————聊天词语标签 -->
                    <div v-show="currentSlide==0">
                        <div
                            id="chatChart"
                            :style="{width: '800px', height: '400px'}"
                            style="margin-left:-300px; margin-top:-0px;"
                        ></div>
                        <div
                            id="chatPie"
                            :style="{width: '1500px', height: '640px'}"
                            style="margin-left:-0px; margin-top:-420px;"
                        ></div>
                        <!-- <el-button @click="test">test</el-button> -->
                    </div>
                    <!-- 🐷第二张幻灯片:聊天次数 -->
                    <!-- 有人会问，欸，不应该聊天次数在第一张slide吗？内容由少到多，由浅入深啊 -->
                    <!-- 😐不😐要😐问😐 -->
                    <!-- vue的动态渲染，与自设的slide滑动效果，与echarts，三观不合 -->
                    <!-- 只有图表在第一页的时候才能显示出来，不然就会有 “显示” -> “消失” 的美·好·奇·迹出现 -->
                    <!-- 不想去找为什么了，就这样吧 v1.0-->
                    <div v-show="currentSlide==1">
                        <div class="slide-content-text">
                            <p>
                                <font size="80">我们相互倾诉了{{conversationNum}}次</font>
                            </p>
                            <p>
                                <font size="25">全世界相互倾诉了{{totalConversationNum}}次</font>
                            </p>
                        </div>
                    </div>
                    <!-- 🐷第三张幻灯片：暂时不知道放什么🐷 -->
                    <!-- 想加东西可以加，先留着了 -->
                    <div v-show="currentSlide==2">
                        <!-- 空·空·如·也 -->
                    </div>
                </div>
                <h2 class="slide-side-text">
                    <span>{{ slide.sublineFirstLine }} ·</span>
                    <span>{{ slide.sublineSecondLine }}</span>
                </h2>
            </div>
        </div>
        <div class="controls-container">
            <button
                class="controls-button"
                v-for="(slide, index) in slides"
                v-bind:class="{ active: index === currentSlide }"
                v-on:click="updateSlide(index)"
            >{{ slide.headlineFirstLine }}</button>
        </div>
        <div class="pagination-container">
            <span
                class="pagination-item"
                v-for="(slide, index) in slides"
                v-bind:class="{ active: index === currentSlide }"
                v-on:click="updateSlide(index)"
            ></span>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            currentSlide: 0,
            isPreviousSlide: false,
            isFirstLoad: true,

            /*🐷🐷🐷🐷🐷🐷🐷🐷🐷🐷后端需要关注的数据🐷🐷🐷🐷🐷🐷🐷🐷🐷🐷*/

            conversationNum: "", // 当前用户聊天次数
            totalConversationNum: "", // 所有用户的聊天次数（聊天记录表一共的条数）

            // conversationDates：聊天日期，需要后端读取到用户：
            //      1）最早聊天的日期
            //      2）今天的日期，
            // 然后写循环将【 1）~ 2）每天（无论有没有聊天）的日期 】都放在这个表内
            conversationDates: ["7-22", "7-23", "7-24", "7-25", "7-26"],

            // conversationNumByDate：对应的每天聊天次数
            // 若当天没聊天，设为0
            conversationNumByDate: ["21", "25", "23", "41", "11"],

            // 你把【后端处理好的】【当前用户】每个标签的出现次数（频率）和标签内容按下列格式传到tagSet中
            getFrom: [],
            tagSet: [
                { value: 335, name: "名字" },
                { value: 310, name: "百度" },
                { value: 400, name: "拜拜" },
            ],
            tagNameSet: ["名字", "百度", "拜拜"],

            /*------------------------------------------------------------------ */

            slides: [
                {
                    headlineFirstLine: "记录标签",
                    headlineSecondLine: "", // 可以实现两行文字
                    sublineFirstLine: "聊天",
                    sublineSecondLine: "时间曲线与标签统计",

                    /*  bgImg: 若是本地的就无法显示，网上url图片链接就可以，disappointing
                        后端大佬忙完后端之后，谁能解决这个问题我跪下来叫👨
                        btw，也可以走纯色风，lmao */
                    bgImg: "",
                },
                {
                    headlineFirstLine: "聊天次数",
                    headlineSecondLine: "",
                    sublineFirstLine: "聊天",
                    sublineSecondLine: "我们之间",
                    bgImg: "",
                },
                {
                    headlineFirstLine: "敬请期待",
                    headlineSecondLine: "",
                    sublineFirstLine: "更多",
                    sublineSecondLine: "期待",
                    bgImg: "",
                },
            ],
        };
    },

    // watch: {
    //     data: {
    //         handler(newValue, oldValue) {
    //             this.drawPie(newValue, oldValue);
    //         },
    //         deep: true,
    //     },
    // },

    computed: {
        options() {
            let that = this;
            let option = {
                title: {
                    text: "聊天标签",
                    left: "center",
                    textStyle: {
                        fontSize: 25,
                        color: "white",
                    },
                },

                tooltip: {
                    trigger: "item",
                    formatter: "{b} : {c} ({d}%)",
                },
                legend: {
                    orient: "vertical",
                    x: "right",
                    y: "center",
                    padding: [0, 350, 0, 0], //[距上方距离，右，下，左]
                    data: that.tagNameSet,
                    textStyle: {
                        fontSize: 16,
                    },
                },
                series: [
                    {
                        type: "pie",
                        radius: "65%",
                        center: ["50%", "50%"],
                        selectedMode: "single",
                        data: that.tagSet.sort(function (a, b) {
                            return a.value - b.value;
                        }),
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: "rgba(0, 0, 0, 0.5)",
                            },
                        },
                        textStyle: {
                            fontSize: 14,
                        },
                    },
                ],
            };
            return option;
        },
    },

    async created() {
        // 关键词和频率数据我放到tagset了，控制台可以打印出来，但是这个数据在图表上不显示，不晓得你是怎么处理数据的--libofan
        // 现在可以显示了--zyt 7.31
        let that = this;
        let userAccount = JSON.parse(window.sessionStorage.getItem("loginForm"))
            .userAccount;
        //获取关键词和频率的请求
        await this.$http
            .get("/message/" + userAccount)
            .then(function (response) {
                // handle success
                //console.log(response);
                that.getFrom = response.data.data;
                that.getFrom.forEach((element) => {
                    // console.log(element);

                    // 下面这句话我加的
                    // 为了显示图表的颜色示意
                    // zyt 7.31
                    // ------------------------------------
                    that.tagNameSet.push(element.userword);
                    // ------------------------------------
                    that.tagSet.push({
                        value: element.fwords,
                        name: element.userword,
                    });
                    console.log(that.tagSet);
                });
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

        //获取单个用户聊天次数的请求
        await this.$http
            .get("/record/" + userAccount)
            .then(function (response) {
                // handle success
                that.conversationNum = response.data.data.length;
                // console.log(response);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

        //获取所有用户聊天次数的请求
        await this.$http
            .get("/record")
            .then(function (response) {
                // handle success
                //  console.log(response);
                that.totalConversationNum = response.data.data;
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
    },

    mounted: function () {
        setTimeout(() => {
            this.drawLine(); // 画图表
            this.drawPie(); // 画饼
        }, 1000);

        this.$nextTick(() => {});

        var productRotatorSlide = document.getElementById("app");
        var startX = 0;
        var endX = 0;

        productRotatorSlide.addEventListener(
            "touchstart",
            (event) => (startX = event.touches[0].pageX)
        );

        productRotatorSlide.addEventListener(
            "touchmove",
            (event) => (endX = event.touches[0].pageX)
        );

        productRotatorSlide.addEventListener(
            "touchend",
            function (event) {
                var threshold = startX - endX;

                if (threshold < 150 && 0 < this.currentSlide) {
                    this.currentSlide--;
                }
                if (
                    threshold > -150 &&
                    this.currentSlide < this.slides.length - 1
                ) {
                    this.currentSlide++;
                }
            }.bind(this)
        );
    },
    methods: {
        updateSlide(index) {
            index < this.currentSlide
                ? (this.isPreviousSlide = true)
                : (this.isPreviousSlide = false);
            // 设置isPreviousSlide主要是为了确定是否需要滑动效果😄
            this.currentSlide = index;
            this.isFirstLoad = false;
        },

        drawLine() {
            var converChart = this.$echarts.init(
                document.getElementById("chatChart")
            );
            converChart.setOption({
                title: {
                    text: "每日聊天次数",
                    left: "center",
                    textStyle: {
                        fontSize: 25,
                        color: "white",
                    },
                },
                tooltip: {},
                xAxis: {
                    type: "category",
                    data: this.conversationDates,
                },
                yAxis: {
                    type: "value",
                },
                series: [
                    {
                        data: this.conversationNumByDate,
                        type: "line",
                        smooth: true,
                    },
                ],
                lineStyle: {
                    color: "#CD5C5C",
                },
            });
        },

        drawPie() {
            var tagPie = this.$echarts.init(document.getElementById("chatPie"));
            tagPie.setOption(this.options);
        },
    },
};
</script>

<style lang="scss" scoped>
html {
    box-sizing: border-box;
}

*,
*:before,
*:after {
    box-sizing: inherit;
}

@mixin media($breakpoint) {
    @media (max-width: $breakpoint) {
        @content;
    }
}

@mixin media-min($breakpoint) {
    @media (min-width: $breakpoint) {
        @content;
    }
}

@mixin media-height($breakpoint) {
    @media (max-height: $breakpoint) {
        @content;
    }
}

$whitespace-height: 50px;
$left-offset: 13vw;
$rect-width: 48vh;
$rect-height: 62vh;
$rect-border-width: 5vh;
$control-btn-padding: 1.6rem;
$control-active-btn-offset: 0.3rem;
$left-offset-small: 9vw;
$rect-height-small: 20vw;
$rect-width-small: 16vw;
$rect-border-width-small: 5vh;

%callout {
    font-family: "Montserrat";
    text-transform: uppercase;
    color: #fff;
    letter-spacing: 0.12rem;
    font-size: 1rem;
    line-height: 1;
}

[v-cloak] {
    opacity: 0;
}

body {
    cursor: default;
    ::selection {
        background-color: rgba(46, 49, 52, 0.7);
        color: #f5f5f1;
    }
    ::-moz-selection {
        background-color: rgba(46, 49, 52, 0.7);
        color: #f5f5f1;
    }
}

.wrapper {
    height: calc(100vh - #{$whitespace-height});
    min-height: 36rem;
    position: relative;
    @include media(630px) {
        height: 100vh;
        min-height: 0;
    }
}

.slide {
    &-wrapper {
        background-size: cover;
        height: 100%;
        background-position: center center;
        position: absolute;
        width: 100%;
        background-blend-mode: darken;
        &:nth-child(1) {
            background-color: rgba(115, 129, 153, 0.4);
            &:before {
                background-color: rgba(115, 129, 153, 0.25);
            }
            .slide-content-text {
                text-shadow: 2px 5px 45px rgba(85, 96, 113, 0.25);
            }
        }
        &:nth-child(2) {
            background-color: rgba(144, 171, 184, 0.7);
            &:before {
                background-color: rgba(144, 171, 184, 0.3);
            }
            .slide-content-text {
                text-shadow: 2px 5px 45px rgba(121, 142, 152, 0.2);
                font-size: 80px;
            }
        }
        &:nth-child(3) {
            background-color: rgba(86, 125, 156, 0.5);
            &:before {
                background-color: rgba(86, 125, 156, 0.2);
            }
            .slide-content-text {
                text-shadow: 2px 5px 55px rgba(57, 83, 103, 0.4);
            }
        }
        &:before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            z-index: 1;
        }
    }
    &-inner {
        position: relative;
        z-index: 2;
        height: 100%;
        overflow: hidden;
    }
    &-bg-text {
        font-family: "Playfair Display";
        color: #000;
        font-size: 42vh;
        line-height: 0.8;
        opacity: 0.03;
        font-weight: 900;
        margin-top: -4rem;
        position: absolute;
        top: 50%;
        left: 5vw;
        transform: translateY(-50%);
        > p:last-child {
            padding-left: 4rem;
        }
    }
    &-content {
        color: #fff;
        margin-top: 5rem;
        position: absolute;
        top: 50%;
        left: calc(#{$left-offset} + (0.7) * #{$rect-width});
        transform: translateY(-50%);
        display: flex;
        flex-direction: column;
        @include media(1000px) {
            left: calc(#{$left-offset} + 1rem);
        }
        @include media-height(730px) {
            top: 30%;
            transform: translateY(-30%);
            left: calc(#{$left-offset-small} + (0.7) * #{$rect-width-small});
        }
        &-text {
            font-family: "Playfair Display";
            font-size: 7rem;
            letter-spacing: 0.2rem;
            line-height: 0.87;
            font-weight: 700;
            will-change: auto;
            @include media-height(790px) {
                font-size: 7rem;
            }
            @include media(1150px) {
                font-size: 7rem;
            }
            @include media(840px) {
                font-size: 5.5rem;
            }
            @include media(630px) {
                margin-bottom: 5.5rem;
            }
            @include media(500px) {
                font-size: 3.5rem;
            }
            > p:last-child {
                padding-left: 3rem;
            }
        }
        &-cta {
            @extend %callout;
            cursor: pointer;
            align-self: flex-start;
            margin-top: 4.5rem;
            margin-left: calc((0.3) * 48vh + 4.5rem);
            padding: 0.9rem 2.2rem;
            border-left: 1px solid #fff;
            border-right: 1px solid #fff;
            transition: 0.18s ease-in-out;
            font-weight: 700;
            @include media(1000px) {
                background-color: rgba(255, 255, 255, 0.3);
                padding-top: 1.2rem;
                padding-bottom: 1.2rem;
            }
            @include media(630px) {
                display: none;
            }
            &:hover {
                color: #000;
                background-color: #fff;
            }
        }
    }
    &-rect {
        height: $rect-height;
        width: $rect-width;
        border-image-slice: 10%;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        left: $left-offset;
        border-width: $rect-border-width;
        border-style: solid;
        box-shadow: 2px 2px 90px 30px rgba(41, 50, 61, 0.22);
        will-change: auto;
        @include media-height(790px) {
            left: $left-offset-small;
            height: $rect-height-small;
            width: $rect-width-small;
            border-width: $rect-border-width-small;
        }
        @include media-height(730px) {
            top: 30%;
            transform: translateY(-30%);
        }
        &-filter {
            filter: brightness(110%) contrast(110%) saturate(110%);
        }
    }
    &-side-text {
        @extend %callout;
        position: absolute;
        left: calc(#{$left-offset} - 3rem);
        writing-mode: vertical-rl;
        top: calc((50% - (#{$rect-height} / 2)) + (#{$rect-border-width} / 2));
        @include media-height(790px) {
            left: calc(#{$left-offset-small} - 3rem);
            top: calc(
                (50% - (#{$rect-height-small} / 2)) +
                    (#{$rect-border-width-small} / 2)
            );
        }
        @include media-height(730px) {
            top: calc(
                (40% - (#{$rect-height-small} / 2)) +
                    (#{$rect-border-width-small} / 2)
            );
        }
        > span:first-child {
            font-weight: 700;
        }
        &:after {
            content: "";
            width: 1px;
            background-color: #fff;
            height: 40px;
            display: block;
            position: absolute;
            top: calc(100% + 25px);
            left: 50%;
            transform: translateX(-50%);
        }
    }
}

.controls {
    &-container {
        position: absolute;
        z-index: 200;
        display: flex;
        bottom: 0;
        right: 0;
        align-items: flex-end;
        @include media(630px) {
            display: none;
        }
    }
    &-button {
        @extend %callout;
        cursor: pointer;
        background-color: rgba(208, 206, 204, 0.32);
        border: 0;
        padding: $control-btn-padding 2.2rem;
        flex-basis: 0;
        flex-grow: 1;
        min-width: 15rem;
        transition: 0.25s ease-in-out;
        outline: 0;
        @include media(730px) {
            padding: 1.2rem 1.4rem;
            min-width: 13rem;
        }
        &:not(.active) {
            &:hover {
                color: #000;
                background-color: #fff;
            }
        }
        &.active {
            cursor: default;
            font-weight: 700;
            background-color: #3b3e45;
            padding-top: $control-btn-padding + $control-active-btn-offset;
            padding-bottom: $control-btn-padding + $control-active-btn-offset;
            margin-bottom: -$control-active-btn-offset;
            position: relative;
            @include media(730px) {
                padding-top: 1.4rem;
                padding-bottom: 1.4rem;
                margin-bottom: -0.15rem;
            }
            &:after {
                content: "";
                background-color: #e3e3e3;
                height: 5px;
                width: calc(100% - 8px);
                position: absolute;
                top: 100%;
                left: 4px;
            }
        }
        &:not(.active) + & {
            border-left: 1px solid rgba(255, 255, 255, 0.2);
        }
    }
}

.pagination {
    &-container {
        position: absolute;
        z-index: 200;
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        right: 2rem;
        top: 50%;
        transform: translateY(-50%);
        @include media(920px) {
            display: none;
        }
    }
    &-item {
        width: 30px;
        height: 1px;
        background-color: rgba(255, 255, 255, 0.6);
        transition: 0.18s ease-in-out;
        & + & {
            margin-top: 1rem;
        }
        &.active {
            background-color: #fff;
            position: relative;
            transform: translateX(-0.6rem);
            width: 35px;
            &:after {
                content: "";
                height: 4px;
                width: 2px;
                border-radius: 35%;
                background-color: #fff;
                display: inline-block;
                position: absolute;
                right: 0;
                top: 50%;
                transform: translateX(0.6rem) translateY(-50%);
            }
        }
        &:not(.active) {
            cursor: pointer;
            &:hover {
                background-color: #fff;
                width: 35px;
            }
        }
    }
}

// 动画效果🐷
$slide-swipe: 0.9s;
$text-cut-up: 0.5s;
@keyframes slideLeft {
    from {
        transform: translateX(0);
    }
    to {
        transform: translateX(-100%);
    }
}

@keyframes slideRight {
    from {
        transform: translateX(-100%);
    }
    to {
        transform: translateX(0);
    }
}

@keyframes cutTextUp {
    from {
        clip-path: inset(0 0 -10% 0);
    }
    to {
        clip-path: inset(0 0 100% 0);
    }
}

@keyframes cutTextDown {
    from {
        clip-path: inset(100% 0 0 0);
    }
    to {
        clip-path: inset(-10% 0 -20% 0);
        opacity: 1;
    }
}

@keyframes cutTextDownFromTop {
    from {
        clip-path: inset(0 0 100% 0);
    }
    to {
        clip-path: inset(0 0 -30% 0);
        opacity: 1;
    }
}

@keyframes rectMovement {
    0% {
        transform: translateX(0) rotate(0) translateY(-50%);
    }
    60% {
        opacity: 1;
    }
    100% {
        transform: translateX(calc(-#{$rect-width} + -#{$left-offset}))
            rotate(12deg) translateY(-50%);
        opacity: 0;
    }
}

@include media-height(730px) {
    @keyframes rectMovement {
        0% {
            transform: translateX(0) rotate(0) translateY(-30%);
        }
        60% {
            opacity: 1;
        }
        100% {
            transform: translateX(calc(-#{$rect-width} + -#{$left-offset}))
                rotate(12deg) translateY(-30%);
            opacity: 0;
        }
    }
}

@keyframes rectMovementFromRight {
    0% {
        transform: translateX(calc(#{$rect-width})) rotate(12deg)
            translateY(-50%);
        opacity: 0;
    }
    60% {
        opacity: 1;
    }
    100% {
        transform: translateX(0) rotate(0) translateY(-50%);
        opacity: 1;
        @include media-height(730px) {
            transform: translateX(0) rotate(0) translateY(-30%);
        }
    }
}

@include media-height(730px) {
    @keyframes rectMovementFromRight {
        0% {
            transform: translateX(calc(#{$rect-width})) rotate(12deg)
                translateY(-30%);
            opacity: 0;
        }
        60% {
            opacity: 1;
        }
        100% {
            transform: translateX(0) rotate(0) translateY(-30%);
            opacity: 1;
        }
    }
}

@keyframes rectMovementRight {
    0% {
        transform: translateX(calc(-#{$rect-width} + -#{$left-offset}))
            rotate(12deg) translateY(-50%);
    }
    40% {
        opacity: 1;
    }
    100% {
        transform: translateX(0) rotate(0) translateY(-50%);
        opacity: 1;
        @include media-height(730px) {
            transform: translateX(0) rotate(0) translateY(-30%);
        }
    }
}

@include media-height(730px) {
    @keyframes rectMovementRight {
        0% {
            transform: translateX(calc(-#{$rect-width} + -#{$left-offset}))
                rotate(12deg) translateY(-30%);
        }
        40% {
            opacity: 1;
        }
        100% {
            transform: translateX(0) rotate(0) translateY(-30%);
            opacity: 1;
        }
    }
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

.slide-wrapper {
    opacity: 0;
    transition-delay: $slide-swipe + $text-cut-up;
    transition-duration: 0s;
    transition-property: opacity;
    will-change: opacity, transform;
    &:not(.active) {
        animation-delay: $text-cut-up;
        animation-name: slideLeft;
        animation-duration: $slide-swipe;
        animation-timing-function: cubic-bezier(0.18, 0.54, 0.52, 0.93);
        pointer-events: none;
        .slide-content-text > p,
        .slide-side-text {
            animation-name: cutTextUp;
            animation-duration: $text-cut-up;
            animation-timing-function: ease-out;
        }
        .slide-rect {
            animation-name: rectMovement;
            animation-duration: $text-cut-up;
            animation-timing-function: ease;
            animation-fill-mode: forwards;
        }
    }
    &.active {
        transition-delay: 0s;
        opacity: 1;
        .slide-content-text > p {
            opacity: 0;
            animation-delay: $text-cut-up + 0.3s;
            animation-name: cutTextDown;
            animation-duration: $text-cut-up;
            animation-timing-function: ease;
            animation-fill-mode: forwards;
        }
        .slide-rect {
            opacity: 0;
            animation-name: rectMovementFromRight;
            animation-duration: $text-cut-up - 0.05s;
            animation-timing-function: ease;
            animation-fill-mode: forwards;
            animation-delay: $slide-swipe;
        }
    }
    .is-previous & {
        &:not(.active) {
            animation: none;
            .slide-rect {
                animation: none;
            }
        }
        &.active {
            transform: translateX(-100%);
            animation-fill-mode: forwards;
            animation-delay: $text-cut-up;
            animation-name: slideRight;
            animation-duration: $slide-swipe - 0.1s;
            animation-timing-function: cubic-bezier(0.18, 0.54, 0.52, 0.93);
            .slide-rect {
                opacity: 0;
                animation-name: rectMovementRight;
                animation-duration: $text-cut-up;
                animation-timing-function: ease-out;
                animation-fill-mode: forwards;
                animation-delay: $slide-swipe;
            }
        }
    }
}

.first-load {
    .slide-wrapper.active .slide-side-text,
    .slide-wrapper.active .slide-rect,
    .controls-container {
        opacity: 0;
        animation-name: fadeIn;
        animation-delay: 0.3s;
        animation-duration: 0.3s;
        animation-fill-mode: forwards;
        animation-timing-function: ease-in;
    }
    .slide-wrapper.active .slide-content-text > p {
        animation-name: fadeIn;
        animation-delay: $text-cut-up;
        animation-duration: $text-cut-up + 0.2s;
    }
}
</style>