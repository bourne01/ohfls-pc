<template>
    <div class="t-e-s">
        <el-row>
            <el-col :span="6">
                <div class="teacher">
                    <div class="avatar">头像</div>
                    <div class="teacher-name">{{teacherName}}</div>
                    <el-menu
                        default-active="1"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#fff"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                        <el-menu-item index="1">
                    <el-rate
                         v-model="value1"
                        :max="3"
                        show-text
                        :texts="['0%', '10%', '90%']"
                    > 
                    </el-rate>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <el-rate
                            v-model="value2"
                            :max="3"
                            show-text
                            :texts="['0%', '10%', '90%']"
                            > 
                            </el-rate>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <el-rate
                            v-model="value3"
                            :max="3"
                            show-text
                            :texts="['0%', '10%', '90%']"
                            > 
                            </el-rate>
                    </el-menu-item>
                    </el-menu>
                </div>
            </el-col>
            <el-col :span="18">
                    <div class="student-area">
                        <div class="student-evaluate">
                            <div class="student-name">姓名:&nbsp;{{studentName}}</div>
                            <div class="student-id">学号:&nbsp;{{studentID}}</div>
                                <div class="student-rate">
                                    <el-rate
                                        v-model="value4"
                                        :max="3"
                                        show-text
                                        :texts="['一般', '良好', '优秀']"
                                        > 
                                    </el-rate>
                                 </div>
                                <div class="evaluate-date">
                                    {{evaluateDate}}这里是评价的日期
                                </div>
                                <div class="evaluate">
                                    {{studentEvaluate}}
                                </div>
                            </div>
                        <div class="student-evaluate">
                            <div class="student-name">姓名:&nbsp;{{studentName}}</div>
                            <div class="student-id">学号:&nbsp;{{studentID}}</div>
                                <div class="student-rate">
                                    <el-rate
                                        v-model="value4"
                                        :max="3"
                                        show-text
                                        :texts="['一般', '良好', '优秀']"
                                        > 
                                    </el-rate>
                                 </div>
                                <div class="evaluate-date">
                                    {{evaluateDate}}这里是评价的日期
                                </div>
                                <div class="evaluate">
                                    {{studentEvaluate}}
                                </div>
                        </div>
                        <div class="student-evaluate">
                            <div class="student-name">姓名:&nbsp;{{studentName}}</div>
                            <div class="student-id">学号:&nbsp;{{studentID}}</div>
                                <div class="student-rate">
                                    <el-rate
                                        v-model="value4"
                                        :max="3"
                                        show-text
                                        :texts="['一般', '良好', '优秀']"
                                        > 
                                    </el-rate>
                                 </div>
                                <div class="evaluate-date">
                                    {{evaluateDate}}这里是评价的日期
                                </div>
                                <div class="evaluate">
                                    {{studentEvaluate}}
                                </div>
                        </div>
                        <div class="student-evaluate">
                            <div class="student-name">姓名:&nbsp;{{studentName}}</div>
                            <div class="student-id">学号:&nbsp;{{studentID}}</div>
                                <div class="student-rate">
                                    <el-rate
                                        v-model="value4"
                                        :max="3"
                                        show-text
                                        :texts="['一般', '良好', '优秀']"
                                        > 
                                    </el-rate>
                                 </div>
                                <div class="evaluate-date">
                                    {{evaluateDate}}这里是评价的日期
                                </div>
                                <div class="evaluate">
                                    {{studentEvaluate}}
                                </div>
                        </div>
                    </div>
                    
            </el-col>
        </el-row>
    </div>
</template>



<script>
export default {
    data(){
        return{
            teacherName:'翁进迁',
            value1: null,   
            value2: null, 
            value3: null,   
            value4: null,
            studentEvaluate:'97式突击步枪（中国代号：QBZ-97，英文：Type 97 assault rifle），是中国北方工业公司（NORINCO）研制生产的一款专供外贸出口的5.56毫米自动步枪。该型突击步枪延续了5.8毫米突击步枪特点，轻重量和设计紧凑，精度良好和可靠性高。'
    }
}
}
</script>


<style scoped>
    .el-row{
        height:100%;
    }
    .el-col{
        height:100%;
        
    }
    .teacher{
        height: 606px;
        border-right: 2px solid rgb(228, 231, 237);
        padding-top: 60px;
        
    }
    .avatar{
        height: 95px;
        width: 95px;
        border-radius: 50%;
        background-color: aqua;
        margin: 0 auto;
        margin-bottom: 10px;
    }
    
    .el-menu-item{
        padding-top: 40px;
        height: 68px;
        margin-bottom: 20px;
    }
    .el-menu{
        margin-top: 50px;
    }
    .student-area{
        width: 100%;
        height: 606px;
        overflow-y: scroll;
        
    }
    .student-name{
        float: left;
    }
    .student-id{
        float: left;
        margin-left:20%;
    }
    .student-evaluate{
        padding:  0 8% 0 5%;
        margin-top: 50px;
        background-color: black;
        width: 100%;
        

    }
    .evaluate-date{
        float: right;
        margin-top: 20px;
        color: #b5b5c0;
        font-family: 'SimSun'
    }
    .evaluate{
        clear: left;
        float: left;
        width: 100%;
        margin-top: 10px;
        background-color: #fcf9fb;
        font-size: 12px;
        text-align: left;
        margin-bottom: 50px;
        border-radius: 2px;
    }
</style>


<style>
     .t-e-s .el-rate__icon.el-icon-star-on{
        font-size:18px;
    }
    .t-e-s .el-rate__text{
        font-size:12px;
        font-family:'MicrosoftYaHei';
        color:black;
        float: left;
        margin-top:-20px;
        margin-left:40%;
    }
    .t-e-s .el-rate__icon.el-icon-star-off{  
        font-size:18px;
    }
    .t-e-s .el-rate__item {
        margin-right:1px;
    }
    .t-e-s .el-menu-item.is-active{
        background-color:rgb(239,242,244);
    }
    .student-rate{
        clear: left;
        float: left;  
        margin-top:20px;
    }
    .student-rate .el-rate__text{
        font-size:12px;
        font-family:'MicrosoftYaHei';
        color:black;
        padding:0;
        margin:0;
        float: none;
    }
    .student-rate{

    }
    .student-rate{

    }
</style>
  
