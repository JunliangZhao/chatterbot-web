<template>
    <div class="settings">
        <!-------------------------------------------------- 以下为重写，区分 ------------zyt----------------------------------------------->

        <transition name="el-zoom-in-top">
            <div v-show="show" class="transition-box">
                <el-collapse v-model="activeName" accordion>
                    <el-collapse-item name="info">
                        <template slot="title">
                            <font size="3" color="black" style="margin-left:45%">
                                <b v-if="activeName == 'info'">个人信息</b>
                                <div v-else>个人信息</div>
                            </font>
                            <i class="header-icon el-icon-info" style="margin-left:10px"></i>
                        </template>

                        <el-form
                            ref="form"
                            :model="getForm"
                            label-width="80px"
                            style="margin-left:130px"
                        >
                            <!-- <el-form-item label="头像" class="settingLabel"> -->
                            <!--<el-image -->
                            <!--style="width: 60px; height: 60px; margin-left:110px; border-radius: 50%; cursor: pointer;" -->
                            <!--:src="form.avatar?form.avatar:require('../assets/logo.jpg')" -->
                            <!--alt="" -->
                            <!--@click="handleAvatarClicked">-->
                            <!--</el-image>-->
                            <!-- </el-form-item> -->

                            <el-form-item label="帐号名" class="settingLabel">
                                <el-input
                                    v-model="getForm.userName"
                                    style="width:250px; margin-left: 20px;"
                                    :disabled="true"
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="邮箱" class="settingLabel">
                                <el-input
                                    v-model="getForm.userAccount"
                                    style="width:250px; margin-left: 20px;"
                                    :disabled="true"
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="性别" class="settingLabel">
                                <el-input
                                    v-model="getForm.userSex"
                                    style="width:250px; margin-left: 20px;"
                                    :disabled="true"
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="年龄" class="settingLabel">
                                <el-input
                                    v-model="getForm.userAge"
                                    style="width:250px; margin-left: 20px;"
                                    :disabled="true"
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="个性签名" class="settingLabel">
                                <el-input
                                    v-model="getForm.userNote"
                                    style="width:250px; margin-left: 20px;"
                                    :disabled="true"
                                ></el-input>
                            </el-form-item>
                        </el-form>
                    </el-collapse-item>

                    <el-collapse-item name="changePassword">
                        <template slot="title">
                            <font size="3" color="black" style="margin-left:45%">
                                <b v-if="activeName == 'changePassword'">修改密码</b>
                                <div v-else>修改密码</div>
                            </font>
                            <i
                                v-if="activeName == 'changePassword'"
                                class="header-icon el-icon-unlock"
                                style="margin-left:10px"
                            ></i>
                            <i v-else class="header-icon el-icon-lock" style="margin-left:10px"></i>
                        </template>

                        <el-form
                            ref="form"
                            :model="form"
                            label-width="100px"
                            style="margin-left:130px"
                        >
                            <el-form-item label="新的密码" class="settingLabel">
                                <el-input
                                    v-model="form.userPwd"
                                    style="width :250px; margin-left: 20px;"
                                    show-password
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="再次确认密码" class="settingLabel">
                                <el-input
                                    v-model="form.againUserPwd"
                                    style="width :250px; margin-left: 20px;"
                                    show-password
                                ></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button
                                    type="success"
                                    icon="el-icon-check"
                                    circle
                                    @click="onSubmitNewPassword"
                                    style="margin-left:20px;"
                                ></el-button>
                            </el-form-item>
                        </el-form>
                    </el-collapse-item>

                    <el-collapse-item name="changeInfo">
                        <template slot="title">
                            <font size="3" color="black" style="margin-left:44%;">
                                <b v-if="activeName == 'changeInfo'">修改个人信息</b>
                                <div v-else>修改个人信息</div>
                            </font>
                            <i class="header-icon el-icon-edit" style="margin-left:10px"></i>
                        </template>

                        <el-form
                            ref="form"
                            :model="postForm"
                            label-width="100px"
                            style="margin-left:130px"
                        >
                            <el-form-item label="性别" class="settingLabel">
                                <el-radio v-model="postForm.userSex" label="男">男</el-radio>
                                <el-radio v-model="postForm.userSex" label="女">女</el-radio>
                                <el-radio v-model="postForm.userSex" label="保密">保密</el-radio>
                            </el-form-item>

                            <el-form-item label="年龄" class="settingLabel">
                                <el-input
                                    v-model="postForm.userAge"
                                    style="width :70px"
                                    maxlength="2"
                                ></el-input>
                            </el-form-item>

                            <el-form-item label="昵称" class="settingLabel">
                                <el-input v-model="postForm.userName" style="width :250px"></el-input>
                            </el-form-item>

                            <el-form-item label="个性签名" class="settingLabel">
                                <el-input v-model="postForm.userNote" style="width:250px;"></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button
                                    type="success"
                                    icon="el-icon-check"
                                    circle
                                    @click="onSubmitNewInfo"
                                ></el-button>
                            </el-form-item>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </transition>
    </div>
    <!-------------------------------------------------- 以上为重写，区分 ---------------zyt-------------------------------------------->
</template>


<script>
export default {
    data() {
        return {
            /*输入绑定的表单，我改了一些字段，统一数据格式--libofan*/
            // 直接把avatar写死好了，或者去除头像
            form: {
                avatar: "",
                userID: "",
                userName: "",
                userAccount: "",
                againUserPwd: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },

            /*表单 --libofan*/

            //获取用户信息,直接从全局的loginForm中拿取
            getForm: {
                userID: "",
                userName: "",
                userAccount: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },
            //用于修改个人信息，用户提交的新表单
            postForm: {
                userName: "",
                userAccount: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },
            //用于修改密码，用户提交的新表单
            passForm: {
                userName: "",
                userAccount: "",
                userPwd: "",
                userType: "",
                userSex: "",
                userNote: "",
                userAvailable: "",
                userAge: "",
            },
            dialogFormVisible: false,
            activeName: "info", // 默认打开的是第一个小菜单
            show: false,
        };
    },

    created() {
        this.getForm = JSON.parse(window.sessionStorage.getItem("loginForm"));
        setTimeout(() => {
            //定时器,created执行一秒后触发
            this.show = true;
        }, 150);
    },
    
    methods: {
        // ------------------------- 以下函数为新加的，区分 --------------------------
        async onSubmitNewPassword() {
            var pwdLetterReg = /[a-zA-Z]+/;
            var pwdNumReg = /[0-9]+/;

            if (this.getForm.userPwd == this.form.userPwd) {
                this.$notify({
                    title: "提示",
                    message: "新密码不能与旧密码一致",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.form.againUserPwd = this.form.userPwd = "";
                return;
            }

            if (this.form.againUserPwd == "") {
                this.activeName = "";
                this.$notify({
                    title: "提示",
                    message: "已退出密码修改",
                    duration: 3000,
                    type: "info",
                });
                return;
            }

            if (this.form.againUserPwd < 6 || this.form.userPwd > 10) {
                this.$notify({
                    title: "提示",
                    message: "密码长度应在6~10位",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.form.againUserPwd = this.form.userPwd = "";
                return;
            }

            if (!pwdLetterReg.test(this.form.againUserPwd)) {
                this.$notify({
                    title: "提示",
                    message: "密码至少应有1个字母",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.form.againUserPwd = this.form.userPwd = "";
                return;
            }

            if (!pwdNumReg.test(this.form.againUserPwd)) {
                this.$notify({
                    title: "提示",
                    message: "密码至少应有1个数字",
                    duration: 3000,
                    type: "error",
                });
                // 清空输入框
                this.form.userPwd = this.form.againUserPwd = "";
                return;
            } else {
                this.activeName = ""; // 修改完密码后，关闭小菜单
                //注释掉这个提示了，下边根据后端返回数据的不同会有不同的提示
                // this.$notify({
                //     title: '提示',
                //     message: '密码已成功修改',
                //     duration: 3000,
                //     type: 'success'
                // });

                // ------------ 传数据给后端 --------------
                let that = this;
                this.postForm.userPwd = this.form.userPwd;
                this.postForm.userName = this.getForm.userName;
                this.postForm.userAccount = this.getForm.userAccount;
                this.postForm.userAvailable = this.getForm.userAvailable;
                this.postForm.userType = this.getForm.userType;
                this.postForm.userSex = this.getForm.userSex;
                this.postForm.userNote = this.getForm.userNote;
                this.postForm.userAge = this.getForm.userAge;
                let data = this.postForm;
                // console.log(this.postForm);
                //调用axios的post方法
                let url = "/info";
                await this.$http
                    .put(url, data)
                    .then((res) => {
                        // console.log(res);
                        if (res.status == 200) {
                            that.getForm = that.postForm;
                            window.sessionStorage.setItem(
                                "loginForm",
                                JSON.stringify(that.postForm)
                            );
                            // console.log(JSON.parse(window.sessionStorage.getItem('loginForm')));
                            //that.getForm = that.$store.state.loginForm;
                            that.$notify({
                                title: "提示",
                                message: "密码已成功修改",
                                duration: 3000,
                                type: "success",
                            });
                        } else
                            that.$notify({
                                title: "提示",
                                message: "密码修改失败",
                                duration: 3000,
                                type: "fail",
                            });
                    })
                    .catch((err) => {
                        console.log(err);
                    });

                // ---------------------------------------
            }
        },

        handleAvatarClicked() {
            this.$message("请在界面右上角的更多菜单中修改头像");
        },

        async onSubmitNewInfo() {
            //var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            //var phoneReg = /^1[3456789]\d{9}$/;
            var ageReg = /^(([0-9]|[1-9][1-9]|1[0-7][0-9])(\\.[0-9]+)?|100)$/;

            // if(!emailReg.test(this.form.email)){
            //     this.$notify({
            //         title: '提示',
            //         message: '邮箱格式不正确',
            //         duration: 3000,
            //         type: 'error'
            //     });
            //     // 清空输入框
            //     this.form.email = '';
            //     return;
            // }

            // if(!phoneReg.test(this.form.num)){
            //     this.$notify({
            //         title: '提示',
            //         message: '电话格式不正确',
            //         duration: 3000,
            //         type: 'error'
            //     });
            //     // 清空输入框
            //     this.form.num = '';
            //     return;
            // }

            //if (!ageReg.test(this.form.age)) {
             //   this.$notify({
                //    title: "提示",
                //    message: "年龄格式不正确",
                //    duration: 3000,
               //     type: "error",
             //   });
                // 清空输入框
             //   this.form.age = "";
              //  return;
          //  }

            this.activeName = ""; // 修改完个人信息后，关闭小菜单

            // ------------ 传数据给后端 --------------

            let that = this;
            this.postForm.userAccount = this.getForm.userAccount;
            this.postForm.userAvailable = this.getForm.userAvailable;
            this.postForm.userPwd = this.getForm.userPwd;
            this.postForm.userType = this.getForm.userType;
            let data = this.postForm;
            // console.log(this.postForm);
            //调用axios的post方法
            let url = "/info";
            await this.$http
                .put(url, data)
                .then((res) => {
                    console.log(res);
                    if (res.status == 200) {
                        that.getForm = that.postForm;
                        window.sessionStorage.setItem(
                            "loginForm",
                            JSON.stringify(that.postForm)
                        );
                        // console.log(JSON.parse(window.sessionStorage.getItem('loginForm')));
                        this.$notify({
                            title: "提示",
                            message: "个人信息已成功修改",
                            duration: 3000,
                            type: "success",
                        });
                    } else
                        this.$notify({
                            title: "提示",
                            message: "个人信息修改失败",
                            duration: 3000,
                            type: "fail",
                        });
                })
                .catch((err) => {
                    console.log(err);
                });

            // 用户新改变的信息是：1) this.postForm.userAccount 2) this.postForm.userSex 3) this.postForm.userAge
            //4)this.postForm.userNote --libofan

            // 7.23更新：email改不了了，因为是登陆的账号，num改不了了，因为数据库改表太麻烦组员了，没有电话号码的表项

            // ---------------------------------------
        },
    },
};
</script>

<style>
/* 更多样式的设计直接加到template标签对应的组件里了，便于对应查看，不用拉下来浪费时间…… */
.settingLabel .el-form-item__label {
    font-size: 110%;
    margin: auto;
    text-align: middle;
}
</style>

