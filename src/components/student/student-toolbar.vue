<template>
    <div class="student-toolbar">
        <el-button icon="el-icon-plus" size="small">新增学生</el-button>
        <el-button icon="el-icon-upload2" size="small">导入学生</el-button>
        <el-button icon="el-icon-download" size="small">下载模板</el-button>
        <el-input placeholder="请输入学生姓名或学号">
            <el-button slot="append" icon="el-icon-search"></el-button>            
        </el-input>
    </div>
</template>

<script>
export default {
    
}
</script>

<style>
    .student-toolbar .el-input__inner{
        height:32px;
        line-height:32px;
    }
    .student-toolbar .el-input__icon{
        line-height: 32px;
    }
    .student-toolbar .el-input-group__append{
        padding:0 10px;
    }
</style>


<style scoped>
    .student-toolbar{
        display: flex;
        height:90px;
        align-items:center;
        width:100%;
        box-sizing:border-box;
        flex-wrap: wrap;
    }
    .el-button{
        padding-top:0;
        padding-bottom:0;
        height:32px;
        /* line-height:32px; */
        background-color:#409EFF;
        color:#fff;
        font-size:14px;                
    }
    .el-button + .el-button{
        margin-left:20px;
    }
    .el-input{
        margin-left:20px;
    }
    .el-input-group{
        width:260px;
    }
</style>

