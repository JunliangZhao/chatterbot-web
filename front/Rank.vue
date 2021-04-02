<template>
    <div class="rank_container">
        <!-- 互动对话框 -->
        <el-dialog title="评价" :visible.sync="dialogVisible" width="30%">
            <span slot="footer" class="dialog-footer">
                <el-button class="iconfont iconzan" type="success" @click="btn_like">牛 逼</el-button>
                <el-button class="iconfont iconcai" type="info" @click="btn_dislike">垃 圾</el-button>
            </span>
        </el-dialog>
        <!-- 走马灯 -->
        <!-- <el-carousel :interval="4000" type="card" height="500px">
            <el-carousel-item v-for="item in 4" :key="item"></el-carousel-item>
        </el-carousel>-->

        <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="item in rankCard" :key="item">
                <h3 class="medium">{{ item }}</h3>
            </el-carousel-item>
        </el-carousel>

        <!-- 排行列表 -->
        <el-table class="table" :data="tableData" :row-class-name="tableRowClassName">
            <el-table-column prop="botMaster" label="用户名" width="180"></el-table-column>
            <el-table-column prop="botName" label="模型名" width="180"></el-table-column>
            <el-table-column prop="botNote" label="备注"></el-table-column>
            <el-table-column prop="botScore" label="得分"></el-table-column>
            <el-table-column prop="botSex" label="机器人性别"></el-table-column>
            <el-table-column prop="botSexualOri" label="机器人性取向"></el-table-column>
            <el-table-column label="互动">
                <!-- 消息按键 -->
                <el-button
                    type="primary"
                    icon="el-icon-message"
                    @click="dialogVisible = true"
                    circle
                ></el-button>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    created() {
        this.getData();
    },
    methods: {
        btn_like() {
            this.dialogVisible = false;
        },
        btn_dislike() {
            this.dialogVisible = false;
        },
        tableRowClassName({ row, rowIndex }) {
            if (rowIndex % 2 === 1) {
                return "warning-row";
            } else if (rowIndex % 2 === 0) {
                return "success-row";
            }
            return "";
        },
        getData() {
            let that = this;
            let url = "/bot//rankList";
            //调用axios的get方法
            this.$http
                .get(url)
                .then((rs) => {
                    if (rs.status == 200) {
                        that.tableData = rs.data.data;
                        console.log(rs);
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
        },
    },
    data() {
        return {
            // 对话框是否可见
            dialogVisible: false,
            // 表单数据
            tableData: [],
            rankCard: [
                "RANK 1 ellie. Score 11675",
                "RANK 2 admin. Score 10000",
                "RANK 3 naughty. Score 9650",
            ],
        };
    },
};
</script>

<style >
/* 走马灯样式 */
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}
/* 走马灯项背景 */
/* .el-carousel__item:nth-child(4n) {
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
} */
/* 排行列表项颜色 */
.el-table .warning-row {
    background: #fdffff;
}

.el-table .success-row {
    background: #ecf5ff;
}
.iconzan {
    height: 50px;
}
.iconcai {
    height: 50px;
}
</style>
<style>
.el-carousel__item h3 {
    background-color: rgba(170, 230, 245, 0.288);
    color: #475669;
    font-size: 24px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(1) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2) {
    background-color: #d3dce6;
}

.medium{
    text-align: center;
}
</style>

<style lang = "less" scoped>
</style>