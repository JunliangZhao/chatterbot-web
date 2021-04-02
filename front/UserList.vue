<template>
    <div id="app">
        <!-- 新增删除按钮 -->
        <el-row>
            <el-button @click="del()" :disabled="this.idList.length>0?false:true" type="danger" icon="el-icon-delete"
                       circle></el-button>
        </el-row>
        <!-- 用户列表 -->
        <template>
            <el-table :data="tableData" @selection-change="selectionChange" stripe style="width: 100%">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="userID" label="ID" width="150">
                </el-table-column>
                <el-table-column prop="userName" label="用户名" width="220">
                </el-table-column>
                <el-table-column prop="userPwd" label="密码" width="220">
                </el-table-column>
                <el-table-column prop="userAccount" label="Email" width="220">
                </el-table-column>
                 <el-table-column prop="userSex" label="性别" width="220">
                </el-table-column>
                 <el-table-column prop="userNote" label="备注" width="220">
                </el-table-column>
                 <el-table-column prop="userAge" label="年龄" width="220">
                </el-table-column>
            </el-table>
        </template>
        <!-- 分页 -->
    </div>
</template>

<script>
    export default {
        data() {
            return {
                //列表相关
                tableData: [],
                idList: [],
                //增加表单相关
                form: {
                    userID:'',
                    userName: '',
                    userAccount: '',
                    userPwd: '',
                    userType: '',
                    userSex: '',
                    userNote:'',
                    userAvailable:'',
                    userAge:'',
                },
                dialogFormVisible: false,
            }
        },
        //页面初始化时调用
        created() {
            this.getData();
        },
        methods: {
            //通过axios请求服务端获取数据，显示用户列表
            async getData() {
                let that = this;
                let url = "user"
                //调用axios的get方法
               await this.$http.get(url).then(
                    rs=>{if(rs.status == 200){
                        that.tableData = rs.data.data;
                        console.log(rs);
                    } }
                ).catch(err=>{
                    console.log(err);
                });
            },
            //行选择触发事件
            selectionChange(selection) {
                this.idList = [];
                // console.log(selection);
                selection.forEach(element => {
                    this.idList.push(element.userID);
                });
                console.log(this.idList);
            },
            //删除事件
            async del() {
                if (this.idList.length > 0) {
                    let that = this;
                    console.log(this.idList);
                    let url = "user/" + this.idList;
                    await this.$http.delete(url).then(
                         rs=>{
                             console.log(rs);
                             if (rs.status == 200) {
                                 //重新获取数据
                                 that.getData();
                             }
                         }).
                     catch(err=>{
                         console.log(err);
                     });
                    }
                }
            },
    }
</script>

<style scoped lang = "less">
    .el-input{
        width:60%;
    }
</style>