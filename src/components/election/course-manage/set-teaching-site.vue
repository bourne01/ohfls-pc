<template>
    <div>
     <el-dropdown>
  <span class="el-dropdown-link">
    {{romName}}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item>黄金糕</el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>      
    </div> <!--选择授课场地的下拉菜单-->  <!--授课场地列-->  
</template>

<script>
import {mapActions,mapState} from 'vuex'
export default {
   props:['site'],
   computed:{
      ...mapState('election',{
          courseTable:state =>state.courseTable
      })
   },
   methods:{
        ...mapActions('election',['getCourseTable'])
   },
   mounted(){
    //   let url = 'selCou!query2.action';
    //   let params = {start:0};
    //   this.getCourseTable({url,params})
   }
    
}

</script>
<style scoped>
    .avatar{                                    /*教师头像样式*/
        background-color: black;
        height: 45px;
        width: 45px;
        border-radius: 50%;
        margin: 0 -15% 0 15%;
        float: left;
    }
    .teacher-name{                              /*教师名字位置*/
         margin:  9px 0 0 20%;
        float: left;
    }
    .avatar-list{                         /*头像列表样式*/
        height: 45px;
        width: 45px;
        background-color: #5b9bfd;
        float: left;
        border-radius: 50%;
        margin-right: 10px;
        margin-top:-5px;
    }
    .el-dropdown-menu {                         /*下拉菜单统一宽度*/
        width: 160px;
    }
    .el-icon-arrow-down.el-icon--right{       /*下拉菜单的颜色*/
        color:blue;
        font-weight: bold;
    }
    .el-dropdown{
        margin-top: 9px;
        display: inline-block;
    }
</style>
































