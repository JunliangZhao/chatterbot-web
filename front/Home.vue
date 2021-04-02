<template>
    <el-container>
        <!-- contatiner布局 -->

        <!-- 头部 -->
        <el-header>
            <!-- 头像 -->
            <!-- <img src="../assets/logo.jpg" alt /> 达不到效果-->
            <!-- <el-avatar src="../assets/logo.jpg"></el-avatar> 达不到效果-->
            <!-- <el-button class="avatar" type="info" @click="changeAvatar"></el-button> 达不到效果-->
            <!-- <el-avatar icon="el-icon-user-solid" class=""></el-avatar> 达不到效果-->
            <el-image
                style="width: 60px; height: 60px"
                :src="avatar?avatar:require('../assets/logo.jpg')"
                alt
                @click="handleAvatarClicked"
                class="useravatar"
            ></el-image>

            <!-- 退出登陆和更多按钮 -->
            <el-row>
                <el-button class="exit-btn" type="info" @click="logout">
                    <span>退出登录</span>
                </el-button>
                <el-dropdown @command="handleCommand">
                    <el-button type="info" class="more-btn">
                        更多
                        <i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="fullscreen">
                            全屏
                            <el-switch
                                v-model="fullscreen"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                @change="handleFullscreen"
                            ></el-switch>
                        </el-dropdown-item>

                        <el-dropdown-item v-if="false" command="botVisible">
                            机器人
                            <el-switch
                                v-model="botVisible"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                @change="handleBotVisible"
                            ></el-switch>
                        </el-dropdown-item>

                        <el-dropdown-item command="?????">个人中心</el-dropdown-item>
                        <el-dropdown-item command="changeAvatar">修改头像</el-dropdown-item>
                        <!-- 可以继续加 -->
                    </el-dropdown-menu>
                </el-dropdown>
            </el-row>

            <!-- 头像上传对话框 -->
            <el-dialog
                title="上传头像"
                :visible.sync="changeAvatarDialogVisible"
                width="30%"
                :before-close="handleClose"
                center
                class="uploadAvatarDialog"
            >
                <!-- 在这不采用elementUI的el-input，会有错误 -->
                <!-- 用label包装html自带的input，以修改web上最难看的input类型:文件上传 -->
                <label class="avatarInput">
                    <div class="select-button">
                        <span v-if="tmpAvatar==''">选择图像文件</span>
                        <span v-else>已选择</span>
                    </div>
                    <input
                        type="file"
                        accept="image/gif, image/jpeg, image/jpg, image/png"
                        @change="fileChange"
                    />
                </label>
                <span slot="footer" class="dialog-footer">
                    <el-button icon="el-icon-close" circle @click="handelCancelAvatar"></el-button>
                    <el-button
                        type="success"
                        icon="el-icon-check"
                        circle
                        @click="handelConfirmAvatar"
                    ></el-button>
                </span>
            </el-dialog>
        </el-header>

        <!-- 主体 -->
        <el-container>
            <!-- 侧边栏 -->
            <el-menu
                default-active="activePath"
                class="sideBar"
                background-color="#F5F5F5"
                text-color="#000000"
                active-text-color="#2894FF"
                :router="true"
                :collapse="isCollapse"
            >
                <el-menu-item index="welcome" class="sideBarItem">
                    <i class="el-icon-guide"></i>
                    <span slot="title">欢迎</span>
                </el-menu-item>

                <!-- nobody 7.27 -->
                <el-menu-item index="records" class="sideBarItem">
                    <i class="el-icon-document"></i>
                    <span slot="title">记录</span>
                </el-menu-item>

                <el-submenu index="#" class="sideBarItem">
                    <template slot="title">
                        <img src="../assets/robot_icon.png" width="25px" height="25px" />
                        <!-- 7.27 加了font标签是为了在机器人前面加空格，对齐文字 -->
                        <span slot="title"><font> 机器人</font></span>
                    </template>
                    <el-menu-item-group>
                        <!-- nobody 7.27 -->
                        <!-- <el-menu-item index="records">记录</el-menu-item> -->
                        <el-menu-item index="model">聊天</el-menu-item>
                        <el-menu-item index="rank">排行榜</el-menu-item>
                        <el-menu-item index="analysis">大数据</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-menu-item index="userlist" class="sideBarItem" v-if="this.Form.userType==true">
                    <i class="el-icon-user-solid"></i>
                    <span slot="title">用户管理</span>
                </el-menu-item>

                <el-menu-item index="settings" class="sideBarItem">
                    <i class="el-icon-setting"></i>
                    <span slot="title">个人设置</span>
                </el-menu-item>

                <el-menu-item index="about" class="sideBarItem">
                    <i class="el-icon-info"></i>
                    <span slot="title">关于我们</span>
                </el-menu-item>
            </el-menu>

            <!-- 主体内容 -->
            <el-main>
                <!-- 主体内容重定向 -->
                <!-- router-view占位符 -->
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>
export default {
    name: "avatar", // 头像

    data() {
        return {
            Form: {
                userName: "",
                userAccount: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },
            state: {
                "100": 1,
                "101": 1,
                "102": 1,
                "103": 1,
                "104": 1,
                "105": 1,
                "106": 1,
            },
            list: {},
            mainMenuList: [],
            subMenuList: [],
            iconsObject: {
                "100": "iconfont iconguanliyuan",
                "101": "iconfont iconxingzhuang",
                "102": "iconfont iconjilu",
                "103": "iconfont iconpaixingbang",
                "104": "iconfont iconmoxing",
                "105": "iconfont iconshoucang-copy",
                "106": "iconfont iconshezhi-copy",
            },
            activePath: "/userlist",
            //url: require("../assets/logo.jpg"), // elementUI的bug，需要加require函数，正确解析el-image组件
            changeAvatarDialogVisible: false,
            avatar: "", // 头像
            tmpAvatar: "", // 之前的头像
            isCollapse: true,
            fullscreen: false,
            botVisible: true,
        };
    },

    //onload事件
    created() {
        this.Form = JSON.parse(window.sessionStorage.getItem("loginForm"));
        // console.log(this.Form);
        // //查询menuList
        // this.getMenuList();
        this.activePath = window.sessionStorage.getItem("activePath"); // 取出session中的path,动态修改activePath
        // console.log(this.activePath+'lbf')
    },

    mounted() {},

    methods: {
        // 安全退出
        logout() {
            window.sessionStorage.clear(); //清除session
            this.$router.push("/login"); //回到首页
        },
        // // 获取导航菜单方法
        // async getMenuList(){
        //     let that = this;
        //     this.$http.get("/mainmenu").then(
        //         rs=>{if(rs.status == 200){
        //             console.log(rs);
        //             that.mainMenuList = rs.data.data.records;
        //             console.log(that.mainMenuList);
        //         } }
        //     ).catch(err=>{ console.log(err); });
        //     this.$http.get("/submenu").then(
        //         rs=>{if(rs.status == 200){
        //             console.log(rs);
        //             that.subMenuList = rs.data.data.records;
        //             console.log(that.subMenuList);
        //         } }
        //         ).catch(err=>{ console.log(err); });
        // },

        // 保存路径
        saveNavState(activePath) {
            window.sessionStorage.setItem("activePath", activePath); // 存放在session中
            this.activePath = activePath;
        },

        // 用户点击“更多”下拉菜单的对应项时，处理对应的事件
        handleCommand(event) {
            if (event == "?????") {
                this.$message("欢迎来到个人中心");
                this.$router.push("/settings"); // 不太清楚是不是这样子跳转
            } else if (event == "changeAvatar") {
                this.changeAvatar();
            } else if (event == "fullscreen") {
                this.handleFullscreen();
            } else if (event == "botVisible") {
                this.handleBotVisible();
            }
        },

        // 处理全屏请求
        handleFullscreen() {
            let element = document.documentElement;
            if (this.fullscreen) {
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.msExitFullscreen) {
                    document.msExitFullscreen();
                }
            } else {
                if (element.requestFullscreen) {
                    element.requestFullscreen();
                } else if (element.webkitRequestFullScreen) {
                    element.webkitRequestFullScreen();
                } else if (element.mozRequestFullScreen) {
                    element.mozRequestFullScreen();
                } else if (element.msRequestFullscreen) {
                    element.msRequestFullscreen();
                }
            }
            this.fullscreen = !this.fullscreen;
        },

        // 弹出修改头像操作框
        changeAvatar() {
            this.tmpAvatar = "";
            this.changeAvatarDialogVisible = true;
        },

        // 关闭修改头像对话框之前的确认
        handleClose(done) {
            this.changeAvatarDialogVisible = false;
            this.$message("头像上传已取消");
        },

        // 处理用户确认头像的上传
        handelConfirmAvatar() {
            this.changeAvatarDialogVisible = false; // 关闭修改框
            this.avatar = this.tmpAvatar;
            var data = new FormData();
            data.appendTo("file", this.avatar);
            $ajax({
                type: "POST",
                data: data,
                processData: false,
                contentType: false,
                success: function (res) {},
                error: function (err) {},
            });
        },

        // 处理用户取消修改图像
        handelCancelAvatar() {
            this.changeAvatarDialogVisible = false; // 关闭修改框
        },

        // 上传文件内容改变调用该函数
        fileChange(e) {
            var that = this;
            var file = e.target.files[0]; // 无论选多少个图片都是默认取第一个
            var reader = new FileReader();
            reader.onload = function (e) {
                that.tmpAvatar = e.target.result;
            };
            reader.readAsDataURL(file);
        },

        // 处理头像点击事件
        handleAvatarClicked() {
            this.isCollapse = !this.isCollapse;
        },

        // 处理切换机器人显示状态/隐藏状态
        handleBotVisible() {
            this.botVisible = !this.botVisible;
        },
    },
};
</script>


<style lang='less' scoped>
//布局器样式
.home-container {
    height: 100%;
}

//头部样式
.el-header {
    background-color: #272727;
    display: flex;
    justify-content: space-between;
    padding-left: 0.5%;
    padding-right: 0.5%;
    font-size: 40px;
}

//侧边样式
.sideBar {
    background-color: #ffffff;
    border: 0.5px solid #e0e0e0;
    min-height: 900px; // 侧边条最小高度
    .el-menu {
        border-right: none; //解决对不齐现象
        font-weight: 500;
    }
}

.sideBar:not(.el-menu--collapse) {
    width: 200px;
    min-height: 900px; // 侧边条最小高度
}

//主体样式
.el-main {
    background-color: #fbfbff;
    border: 0.5px solid #e0e0e0;
}

// 以下为退出按钮的渐变文字显示等设置
.exit-btn {
    display: inline-block;
    border-radius: 4px;
    background-color: transparent;
    border: none;
    text-align: center;
    width: 150px;
    transition: all 0.5s;
    cursor: pointer;
    margin: 5px;
}
.exit-btn span {
    cursor: pointer;
    display: inline-block;
    position: relative;
    transition: 0.5s;
}
.exit-btn:hover {
    background-color: transparent;
    color: rgb(255, 255, 255);
}
.exit-btn:checked {
    background-color: transparent;
    color: rgb(255, 255, 255);
}
.exit-btn:active {
    background-color: transparent;
    color: rgb(255, 255, 255);
}
.exit-btn span:after {
    content: "x";
    position: absolute;
    opacity: 0;
    top: 0;
    right: -20px;
    transition: 0.5s;
}
.exit-btn:hover span {
    padding-right: 25px;
}
.exit-btn:hover span:after {
    opacity: 1;
    right: 0;
}
.exit-btn:focus {
    background-color: transparent;
}

// 以下为“更多”按钮的样式
.more-btn {
    background-color: transparent;
    border: none;
}
.more-btn:hover {
    background-color: transparent;
}
.more-btn:focus {
    background-color: transparent;
}
.more-btn:focus {
    background-color: rgb(54, 54, 54);
}

// // 二级菜单 修改不了？
// .secondmenu{
//     background: red;
// }

// .avatar{
//     background: url("../assets/logo.jpg");
//     contain:initial;
//     width: 5%;
//     height: 100%;
//     border-radius: 50%;
// }

// 用户头像
.useravatar {
    width: 5%;
    height: 100%;
    border-radius: 50%;
    cursor: pointer;
}

// 用户头像修改框
.avatarInput {
    border-block-color: red;
    outline: none;
    //opacity: 0;
    cursor: pointer;
    button {
        width: 100%;
        height: 30px;
        margin-top: 20px;
        text-align: center;
        border: 1px solid #ccc;
        color: #fff;
        font-size: 14px;
        background: pink;
    }
}

// 上传头像文本框和按钮的样式修改
.avatarInput > .select-button {
    padding: 1rem;
    color: white;
    background-color: #ffc0cb;
    border-radius: 0.3rem;
    text-align: center;
    font-weight: bold;
}

// 把原本的很丑的html的显示样式隐藏
.avatarInput > input[type="file"] {
    display: none;
}

// 7.26修改边框bug
.el-main {
    padding: 0%;
    border: rgba(255, 255, 255, 0);
}
</style>

