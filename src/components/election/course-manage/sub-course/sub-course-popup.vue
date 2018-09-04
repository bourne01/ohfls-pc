<template>
    <div class="sub-course-popup">
            
    <el-dialog
        :visible.sync="openSubCourse"
        width="62.55%"
        :modal-append-to-body="false"   
    >
       <span slot="title">
            <el-button class="add-sub-course" @click="coursePopup.isPop=false">新增子课程</el-button>
        </span>
    <el-table :data="gridData"
     :span-method="objectSpanMethod"
    >
        <el-table-column
            property="supCourse"
            label="父课程名称"
            width="230" 
            align="left"
            >
        </el-table-column>
        <el-table-column property="subCourse" label="子课程名称" width="220">
            
        </el-table-column>
        <el-table-column property="address" label="授课场地" width="149">

        </el-table-column>
        <el-table-column property="dae" label="授课教师" width="200">
            <template slot-scope="scope" >
                <div class="avatar"></div>
              <span class="teacher-name" >{{scope.row.teacher}}</span> 
          </template>         
        </el-table-column>
        <el-table-column  label="课程状态" width="240">
            <template slot-scope="scope"> 
              <sub-course-state :subState="scope.row.subState"></sub-course-state>
            </template>
        </el-table-column>
        <el-table-column property="address" label="操作" width="142">
            <template slot-scope="scope"> 
                <!-- <el-button type="text" 
                size="small"
                 class="edit" 
                 @click="openSubCourse=true" >编辑</el-button>     -->
                <el-button type="text" 
                 size="mini"
                  @click.native.prevent="deleteRow(scope.$index,  gridData)"
                  >                                                        <!--删除图标的样式，默认是不可编辑的，当选择器被选中时可被编辑 -->
                  <img :src="require('../../../../assets/delete-oc.png')" alt=""  >
                </el-button>
            </template>      
        </el-table-column>
  </el-table>
    </el-dialog>
     
    </div>
</template>


<script>
import subCourseState from './sub-course-state.vue'
import {mapState,mapMutations,mapActions} from 'vuex'
export default {
    props:['SubCoursePopup'],
  components:{
      subCourseState
  },
    data(){
        return{
          
            gridData:[{
                    subCourse:'快乐阅读',
                    supCourse:'中小学生快乐阅读',
                    address:'时代方舟',
                    teacher:'啦啦啦',
                    subState: 1,
                },
                { 
                    subCourse:'快乐阅读',
                    address:'时代方舟',
                    teacher:'啦啦啦',
                    subState: 2,
                },
                 { 
                    subCourse:'快乐阅读',
                    address:'时代方舟',
                    teacher:'啦啦啦',
                    subState: 3,
                },
                {
                    subCourse:'快乐阅读',
                    address:'时代方舟',
                    teacher:'啦啦啦',
                    subState: 4,


                },
                {  
                    subCourse:'快乐阅读',
                    address:'时代方舟',
                    teacher:'啦啦啦',
                    subState: 5,

                }]
        }
    },
    methods:{
         deleteRow(index, rows) {            //删除按钮的弹框，并进行进一步的确认或者取消
        this.$confirm('确认删除该课程吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => { 
          rows.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
        },
         objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        if (columnIndex === 0) {
          if (rowIndex % 6 === 0) {
            return {
              rowspan: 5,
              colspan: 1,
            };
          } else {
            return {
              rowspan: 0,
              colspan: 0
            };
          }
        }
          }
    },
    computed:{
       openSubCourse:{ get:function(){                
                 return this.SubCoursePopup.isPop;
            },
            set:function(val){
                this.SubCoursePopup.isPop = val;
            }}
    }

}
</script>

<style scoped>

.add-sub-course{
    float: left;    
    background: url(../../../../assets/add.png) no-repeat;
    background-position: 15% 46%;
    height: 34px;
    background-color: #5b9bfd;
    border: 1px solid #4482f5;
    width: 13.5%;
    margin-left: 3.2%;
    border-radius: 2px;
    color:white;
    padding-left: 4%;
    line-height: 50%;
    font-size: 'MicrosoftYaHei';
    font-size: 16px;
    margin-top:13px;
}
.avatar{
    background-color: black;
    height: 40px;
    width: 40px;
    border-radius: 50%;
    margin: 0 -15% 0 15%;
    float: left;
  }
   .teacher-name{
    margin:  8px 0 0 20%;
    float: left;
  }

</style>

<style>
 
.sub-course-popup .el-dialog  {    /*设置弹框整体的样式*/
   max-height:486px;
    
 }                                                               
.sub-course-popup .el-dialog__header{             /*设置弹框的头部*/
   height: 100px;
   color:white; 
   margin:0px;
 }
 .sub-course-popup  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
.sub-course-popup .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:rgb(182,182,193);
   font-size:28px;
   margin-right:5px;
 }

.sub-course-popup .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:10px;  
    width:100%;
    background-color:rgb(239,242,244);
    height: 100%;
 }


</style>



