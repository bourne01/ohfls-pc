<template>
   <div class="add-teacher">
     <div class="function">
        <el-button type="text" @click="addTeacher" class="add-teacher-function">+ 新增教师</el-button>  <!--新增教师按钮-->
     </div>
           <el-dialog title="新增教师" :visible.sync="addTeacherDialog" width="30%" > <!--新增教师标题-->
                <el-form :model="teacherForm" ref="teacherForm"   label-position="right" inline="true">
                    <el-form-item label="教师姓名" prop="name"  size="mini" >
                      <el-input v-model="teacherForm.name"></el-input>
                    </el-form-item>
                   <el-form-item label="教师编号" prop="id"  size="mini"  >
                      <el-input v-model="teacherForm.name" ></el-input>
                    </el-form-item>
                   <el-form-item label="性别" prop="gender">
                    <el-select v-model="teacherForm.region" placeholder="请选择性别" size="mini" style="float:left;">
                      <el-option label="男" value="man" ></el-option>
                      <el-option label="女" value="woman"></el-option>
                    </el-select>
                  </el-form-item>
                   <el-form-item label="教师简介" prop="summary">
                    <el-input type="textarea" ></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                    <el-input type="textarea" ></el-input>
                    </el-form-item>
                     <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                  </el-form-item>
                </el-form>
           </el-dialog>
       </div> 


</template>
<script>

  export default {
    data() {
      return {  /* 新增教师表格*/
        addTeacherDialog: false,
         teacherForm: {
           name:'',
           id:'',
           gender:'',
           summmary:'',
           remark:'',
         },
        genders:[
          {value:1,label:'男'},
          {value:1,label:'女'}
        ]
         
      }
    },
    methods: {
      toggleSelection(rows) {   /* 全选按钮*/
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
     addTeacher(){ /* 新增教师点击按钮*/
       console.log('addTeacher')
       this.addTeacherDialog = true;
      },
      onSubmit() {
        console.log('submit!');
      }
    }
  } 

</script>
<style scoped>


/* .function{
  background-color: white;
  height: 76px;
} */
.add-teacher-function{                            /* 添加教师功能按钮样式 */
  width: 7%;
  height: 28px;
  background:rgb(91, 155, 253);
  color:#fff;
  line-height: 0%;
  float: left;
  margin: 2% 0 0 2%;
}
</style>
<style>
.list th{       /*  表头字体居中 */
  text-align: center;
  background-color: #E9EEF3;
}
.add-teacher .el-dialog__header{ /* 这是新增标题头部样式*/
  background-color:rgb(13,113,74);
  font-family:'MicrosoftYaHei';
  height: 30px;
 }
.add-teacher .el-dialog__title{  /* 新增教师标题样式*/
  color:white;
  font-size:16px;
  float: left;
  font-family:'	Microsoft YaHei'
}
.add-teacher .el-button--primary{
  width:100px;
  height:35px;
  line-height:50%;
  background-color:rgb(13,113,74);
}
.add-teacher .el-select .el-input__inner{
  width: 240px;
}
.add-teacher .el-input__inner {  /*输入框背景色*/
  background-color:rgb(246,249,251);
}
.add-teacher .el-textarea__inner{  /*输入框背景色*/
  background-color:rgb(246,249,251);
}
.add-teacher .el-dialog__headerbtn .el-dialog__close{
  color:white;
}
</style>