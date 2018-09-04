<template>
     <div class="add-site-popup">
         
             <el-button type="text" @click="addSitedialog = true" class="addSiteButton">+ 新增场地</el-button>  <!--dialog对话框-->
         <el-dialog title="新增教学场地" :visible.sync="addSitedialog" width="30%">
                         <el-form :model="siteForm" ref="siteForm"   >
                    <el-form-item label="教室编号" prop="id"  size="mini" >
                      <el-input v-model="siteForm.id"></el-input>
                    </el-form-item>
                   <el-form-item label="教室名称" prop="name"  size="mini"  >
                      <el-input v-model="siteForm.name" ></el-input>
                    </el-form-item>
                   <el-form-item label="教室类型" prop="gender">
                    <el-select v-model="siteForm.region" placeholder="请选择性别" size="mini" style="float:left;">
                      <el-option label="普通教室" value="room" ></el-option>
                      <el-option label="1" value="room"></el-option>
                    </el-select>
                  </el-form-item>
                    <el-form-item label="坐位数量" prop="id"  size="mini" >
                      <el-input v-model="siteForm.id"></el-input>
                    </el-form-item>
                      <el-form-item label="教学楼" prop="gender">
                    <el-select v-model="siteForm.region" placeholder="请选择性别" size="mini" style="float:left;">
                      <el-option label="普通教室" value="room" ></el-option>
                      <el-option label="1" value="room"></el-option>
                    </el-select>
                  </el-form-item>
                     <el-form-item>
                    <el-button type="primary" @click="onSubmit">确定</el-button>
                    <el-button >取消</el-button>
                  </el-form-item>
                </el-form>
            </el-dialog>
     </div>
</template>

<script>
export default {
    data() {
      return {
        labelPosition: 'right',
        addSitedialog: false,
        // form: {  
        //   name:'',
        //   number:'',
        //   sex:'',
        //   introduce:'',
        //   delivery: false,
        //   Remarks:'',
        //  },
        formLabelWidth: '120px',
        textarea:'',
        siteForm:{}
      }
    },
    methods:{
         onSubmit() {
        console.log('submit!');
      }
    }
}
</script>
<style scoped>
.addSiteButton{ /*新增场地按钮样式*/
  background:rgb(91, 155, 253);
  width: 7%;
  height: 31px;
  color: white;
  float: left;
  margin: 1.1% 0 0 3%;
  line-height: 0%;
  font-size: 16px;
  font-family: 'Microsoft YaHei';
  font-size: 16px;
}
.SearchStyle1{    /*搜索框样式 */
    background-color: #f6f9fb;
    border: 1px solid #e5e9ec;
    border-radius: 4px;
    height: 30px;
    width: 200px;
    float: left;
}
.SearchStyle2{    /*搜索框样式 */
    background-color: #f6f9fb;
    border: 1px solid #e5e9ec;
    border-radius: 4px;
    height: 30px;
    width: 200px;
    float: left;
}

</style>
<style>
.add-site-popup .el-dialog__header{ /* dialog头部样式 */
  background-color:rgb(13,113,74);
  font-family:'MicrosoftYaHei';
  height:30px;
}
.add-site-popup .el-dialog__title{ /* dialog头部字体样式 */
  color:white;
  font-size:18px;
  float: left;
}
.add-site-popup .el-dialog__headerbtn .el-dialog__close{ /*对话框右边关闭符号颜色*/
    color:white;
}
.add-site-popup .el-input--suffix .el-input__inner{
    width: 240px;
}
</style>




