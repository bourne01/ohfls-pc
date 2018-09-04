<template>
    <div class="add-student">
       <el-button 
            type="primary" 
            @click="addStudent=true" 
            class="add-student-button"
            icon="el-icon-plus">添加学生</el-button>       <!--添加学生的按钮-->
        <el-dialog
            title="添加学生"
            :visible.sync="addStudent"
            width="26%"
            top="12vh"
            ref="multipleTable"
            >
                <el-select v-model="selectedGrade" filterable placeholder=全部年级 size="mini">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                 <el-select v-model="selectedClass" filterable placeholder="全部班级" size="mini">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                <form action="#" class="search-area">
                    <input type="text" class="search-id" placeholder="查询学号">
                    <input type="text" placeholder="查询姓名" class="search-name">
                    <button class="search-button"></button>
                 </form>
                <div class="student-table">
                    <el-table
                        ref="multipleTable"
                        :data="studentTable"
                        tooltip-effect="dark"
                        style="width: 100%"
                        @selection-change="handleSelectionChange"
                        height="460px"
                        >
                        <el-table-column
                        type="selection"
                        width="40">
                        </el-table-column>
                        <el-table-column
                        label="姓名"
                        width="159"
                        align="left"
                        >
                        <template slot-scope="scope">{{ scope.row.name }}</template>
                        </el-table-column>
                        <el-table-column
                        prop="ID"
                        label="学号"
                        width="175">
                        </el-table-column>
                        <el-table-column
                        prop="address"
                        label="性别"
                        width="104"
                        show-overflow-tooltip>
                        </el-table-column>
                    </el-table>
                    <div class="footer">
                         <el-checkbox @change="toggleSelection(studentTable)" class="select-all">全选</el-checkbox>
                        <el-button type="primary" >确认添加学生</el-button>
                    </div>
                </div>
        </el-dialog>
    </div>
</template>


<script>
export default {
    data(){
        return{
            selectedGrade:'',
            selectedClass:'',
            addStudent:false,
            studentTable: [{
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        }, {
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        }, {
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        }, {
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        }, {
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        }, {
          ID: '123456789',
          name: '黄书豪',
          address: '男'
        },],
        multipleSelection: [],
        options:[{}]
        }
        
        
    },
        
    methods:{
         toggleSelection(rows) {   /* 全选按钮*/
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
        },
       handleSelectionChange(val) {
        this.multipleSelection = val;
        },
    },
   

}
</script>

<style scoped>
    .add-student-button{
        float: left;   
        height: 32px;
        background-color: #5b9bfd;
        border: 1px solid #4482f5;
        line-height: 32px;
        font-size: 16px;
        padding:0 20px;
        border-radius:2px;
        margin-left:40px;
    }
     .search-area{
        float: left;
        clear: both;
        width: 100%;
        margin-top: 22px;
        margin: 5%;
        padding-left: 4.2%
    }
    .search-name{
       background-color: #f6f9fb;
       border: 1px solid #e5e9ec;
       border-radius: 4px 0 0 4px;
       float: left;
       width: 23%;
       height: 26px;
       padding-left: 4.5%;
    }
    .search-button{
      height: 30px;
      width: 8%;
      border: 1px solid #e5e9ec;
      cursor: pointer;
      display: block;
      background: url(../../../assets/search-o.png) no-repeat;
      border-radius: 0 4px 4px 0;
      background-position: 48% 50%;

    }
    .search-id{
        float: left;
        background-color: #f6f9fb;
        border: 1px solid #e5e9ec;
        border-radius: 4px;
        height: 26px;
        margin-right: 7.2%;
        padding-left: 4.5%;
        width: 30.8%
    } 
    .el-select{
        margin-top: 22px;
        height: 30px;
        width: 36%;
        margin-left: 9%;
        float: left;
        margin-right: -2%
    }
    .footer{
        width: 100%;
        height: 40px;
        background-color: #e5e6e7;
        padding-top: 15px;
    }
    .student-table .el-button{
        float: right;
        height: 30px;
        line-height: 50%;
        border: 1px solid #4482f5;
        margin-right: 10%
    }
    .select-all{
        float: left;
        margin: 0.8% 0 0  2.2%;
       
    }
</style>



<style>
 .add-student .el-dialog  {    /*设置弹框整体的样式*/
   height:700px;
   
  
 }                                                               
 .add-student .el-dialog__header{             /*设置弹框的头部*/
   background-color :#0d714a;
   height: 20px;
   color:#707079; 
   margin:0px;
 }
  .add-student  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    color:white;
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
 .add-student .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:white;
   font-size:24px;
   margin-top:-30px;
 }
 
 .add-student .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:0;  
    width:100%;
}

/* .v-modal{
    background-color:Transparent
} */
</style>

