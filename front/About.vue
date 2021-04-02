<template>
    <div>
        <div class="row">
            <!-- 卡片一 -->
            <div class="example-1 card">
                <div class="wrapper">
                    <div class="header">
                        <div class="date">
                            <span class="day">31</span>
                            <span class="month">July</span>
                            <span class="year">2020</span>
                        </div>
                        <ul class="menu-content">
                            <li>
                                <a href="#" class="fa fa-bookmark-o"></a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-heart-o">
                                    <span>18</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-comment-o">
                                    <span>3</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="data">
                        <div class="content">
                            <span class="author">赵君亮</span>
                            <h1 class="title">
                                <a href="#">欢迎界面、设置界面设计，自然语言处理支持，大数据标签提取和统计，产品介绍网站设计</a>
                            </h1>
                            <p class="text">
                                Happy little frog Leap Frog
                                There is a little frog in the happy pond
                                It danced as if it had been possessed by a prince
                                Cool eyes, no frog can compare to the United States
                                One day it will be awakened by the princess
                            </p>
                            <a href="#" class="button">Read more</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 卡片二 -->
            <div class="example-2 card">
                <div class="wrapper">
                    <div class="header">
                        <div class="date">
                            <span class="day">31</span>
                            <span class="month">July</span>
                            <span class="year">2020</span>
                        </div>
                        <ul class="menu-content">
                            <li>
                                <a href="#" class="fa fa-bookmark-o"></a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-heart-o">
                                    <span>18</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-comment-o">
                                    <span>3</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="data">
                        <div class="content">
                            <span class="author">朱云天</span>
                            <h1 class="title">
                                <a href="#">登陆注册界面、Home界面、记录界面、大数据界面设计，前端数据显示逻辑设计，界面修改和美化</a>
                            </h1>
                            <p class="text">I'm nobody. A pig who enjoys coding.</p>
                            <a href="#" class="button">Read more</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 卡片三 -->
            <div class="example-3 card">
                <div class="wrapper">
                    <div class="header">
                        <div class="date">
                            <span class="day">31</span>
                            <span class="month">July</span>
                            <span class="year">2020</span>
                        </div>
                        <ul class="menu-content">
                            <li>
                                <a href="#" class="fa fa-bookmark-o"></a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-heart-o">
                                    <span>18</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-comment-o">
                                    <span>3</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="data">
                        <div class="content">
                            <span class="author">项桂巳雨</span>
                            <h1 class="title">
                                <a href="#">机器人模型设计，聊天界面及功能设计，socket通信支持，聊天询问搜索的爬虫功能实现</a>
                            </h1>
                            <p class="text">do not drop the sea and makes difficult to garland</p>
                            <a href="#" class="button">Read more</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 卡片四 -->
            <div class="example-4 card">
                <div class="wrapper">
                    <div class="header">
                        <div class="date">
                            <span class="day">31</span>
                            <span class="month">July</span>
                            <span class="year">2020</span>
                        </div>
                        <ul class="menu-content">
                            <li>
                                <a href="#" class="fa fa-bookmark-o"></a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-heart-o">
                                    <span>18</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="fa fa-comment-o">
                                    <span>3</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="data">
                        <div class="content">
                            <span class="author">李勃帆</span>
                            <h1 class="title">
                                <a href="#">登陆界面、排行界面、关于界面设计，聊天记录、用户数据、机器人数据、大数据标签数据的后端通信支持</a>
                            </h1>
                            <p class="text">Only games and codes can't live up to</p>
                            <a href="#" class="button">Read more</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 简介 -->
        <el-card class="box-card" style="margin-left:20%;">
            <div v-for="(value,index) in text" :key="index" class="text item">{{value}}</div>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            text: [
                "敏捷：快速开发与迭代",
                "效率：每周开发一天，一天开发一周工作量(x)",
                "灵感：来源生活，来源交流",
                "规模：虽然只有四个人，开发的就像一个人做的。",
            ],
        };
    },
    methods: {},
};
</script>

<style lang="scss" scoped>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);
// Variables
$regal-blue: #034378;
$san-juan: #2d4e68;
$bermuda: #77d7b9;
$white: #fff;
$black: #000;
$open-sans: "Open Sans", sans-serif;
// clear-fix mixin
@mixin cf {
    &::before,
    &::after {
        content: "";
        display: table;
    }
    &::after {
        clear: both;
    }
}

* {
    box-sizing: border-box;
}

body {
    background-image: linear-gradient(to right, $regal-blue, $san-juan);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height: 100vh;
    font-family: $open-sans;
}

a {
    text-decoration: none;
}

h1 {
    font-family: $open-sans;
    font-weight: 300;
}

.row {
    max-width: 900px;
    margin: 50px auto 0;
}

// Base styles
.card {
    float: left;
    padding: 0 1.7rem;
    width: 50%;
    .menu-content {
        @include cf;
        margin: 0;
        padding: 0;
        list-style-type: none;
        li {
            display: inline-block;
        }
        a {
            color: $white;
        }
        span {
            position: absolute;
            left: 50%;
            top: 0;
            font-size: 10px;
            font-weight: 700;
            font-family: "Open Sans";
            transform: translate(-50%, 0);
        }
    }
    .wrapper {
        background-color: $white;
        min-height: 540px;
        position: relative;
        overflow: hidden;
        box-shadow: 0 19px 38px rgba($black, 0.3), 0 15px 12px rgba($black, 0.2);
        &:hover {
            .data {
                transform: translateY(0);
            }
        }
    }
    .data {
        position: absolute;
        bottom: 0;
        width: 100%;
        transform: translateY(calc(70px + 1em));
        transition: transform 0.3s;
        .content {
            padding: 1em;
            position: relative;
            z-index: 1;
        }
    }
    .author {
        font-size: 12px;
    }
    .title {
        margin-top: 10px;
    }
    .text {
        height: 70px;
        margin: 0;
    }
    input[type="checkbox"] {
        display: none;
    }
    input[type="checkbox"]:checked + .menu-content {
        transform: translateY(-60px);
    }
}

// First example styles
// 卡片一样式
.example-1 {
    .wrapper {
        background: url(../assets/JunLiangZhao.jpg) center / cover no-repeat;
        &:hover {
            .menu-content {
                span {
                    transform: translate(-50%, -10px);
                    opacity: 1;
                }
            }
        }
    }
    .header {
        @include cf;
        color: $white;
        padding: 1em;
        .date {
            float: left;
            font-size: 12px;
        }
    }
    .menu-content {
        float: right;
        li {
            margin: 0 5px;
            position: relative;
        }
        span {
            transition: all 0.3s;
            opacity: 0;
        }
    }
    .data {
        color: $white;
        transform: translateY(calc(70px + 4em));
    }
    .title {
        a {
            color: $white;
        }
    }
    .button {
        display: block;
        width: 100px;
        margin: 2em auto 1em;
        text-align: center;
        font-size: 12px;
        color: $white;
        line-height: 1;
        position: relative;
        font-weight: 700;
        &::after {
            content: "\2192";
            opacity: 0;
            position: absolute;
            right: 0;
            top: 50%;
            transform: translate(0, -50%);
            transition: all 0.3s;
        }
        &:hover {
            &::after {
                transform: translate(5px, -50%);
                opacity: 1;
            }
        }
    }
}
// Second example styles
// 卡片二样式
.example-2 {
    .wrapper {
        background: url(../assets/YunTianZhu.jpg) center / cover no-repeat;
        &:hover {
            .menu-content {
                span {
                    transform: translate(-50%, -10px);
                    opacity: 1;
                }
            }
        }
    }
    .header {
        @include cf;
        color: $white;
        padding: 1em;
        .date {
            float: left;
            font-size: 12px;
        }
    }
    .menu-content {
        float: right;
        li {
            margin: 0 5px;
            position: relative;
        }
        span {
            transition: all 0.3s;
            opacity: 0;
        }
    }
    .data {
        color: $white;
        transform: translateY(calc(70px + 4em));
    }
    .title {
        a {
            color: $white;
        }
    }
    .button {
        display: block;
        width: 100px;
        margin: 2em auto 1em;
        text-align: center;
        font-size: 12px;
        color: $white;
        line-height: 1;
        position: relative;
        font-weight: 700;
        &::after {
            content: "\2192";
            opacity: 0;
            position: absolute;
            right: 0;
            top: 50%;
            transform: translate(0, -50%);
            transition: all 0.3s;
        }
        &:hover {
            &::after {
                transform: translate(5px, -50%);
                opacity: 1;
            }
        }
    }
}
//卡片三样式
.example-3 {
    .wrapper {
        background: url(../assets/SiYuXiangGui.jpg) center / cover no-repeat;
        &:hover {
            .menu-content {
                span {
                    transform: translate(-50%, -10px);
                    opacity: 1;
                }
            }
        }
    }
    .header {
        @include cf;
        color: $white;
        padding: 1em;
        .date {
            float: left;
            font-size: 12px;
        }
    }
    .menu-content {
        float: right;
        li {
            margin: 0 5px;
            position: relative;
        }
        span {
            transition: all 0.3s;
            opacity: 0;
        }
    }
    .data {
        color: $white;
        transform: translateY(calc(70px + 4em));
    }
    .title {
        a {
            color: $white;
        }
    }
    .button {
        display: block;
        width: 100px;
        margin: 2em auto 1em;
        text-align: center;
        font-size: 12px;
        color: $white;
        line-height: 1;
        position: relative;
        font-weight: 700;
        &::after {
            content: "\2192";
            opacity: 0;
            position: absolute;
            right: 0;
            top: 50%;
            transform: translate(0, -50%);
            transition: all 0.3s;
        }
        &:hover {
            &::after {
                transform: translate(5px, -50%);
                opacity: 1;
            }
        }
    }
}
//卡片四样式
.example-4 {
    .wrapper {
        background: url(../assets/BoFanLi.jpg) center / cover no-repeat;
        &:hover {
            .menu-content {
                span {
                    transform: translate(-50%, -10px);
                    opacity: 1;
                }
            }
        }
    }
    .header {
        @include cf;
        color: $white;
        padding: 1em;
        .date {
            float: left;
            font-size: 12px;
        }
    }
    .menu-content {
        float: right;
        li {
            margin: 0 5px;
            position: relative;
        }
        span {
            transition: all 0.3s;
            opacity: 0;
        }
    }
    .data {
        color: $white;
        transform: translateY(calc(70px + 4em));
    }
    .title {
        a {
            color: $white;
        }
    }
    .button {
        display: block;
        width: 100px;
        margin: 2em auto 1em;
        text-align: center;
        font-size: 12px;
        color: $white;
        line-height: 1;
        position: relative;
        font-weight: 700;
        &::after {
            content: "\2192";
            opacity: 0;
            position: absolute;
            right: 0;
            top: 50%;
            transform: translate(0, -50%);
            transition: all 0.3s;
        }
        &:hover {
            &::after {
                transform: translate(5px, -50%);
                opacity: 1;
            }
        }
    }
}
//简介样式
.text {
    font-size: 14px;
}

.item {
    padding: 18px 0;
}

.box-card {
    width: 1000px;
    text-align: center;
}
</style>