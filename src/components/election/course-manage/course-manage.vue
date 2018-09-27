<template>
    <div class="course-manage">
        <div class="course-manage-header">
                <div class="add-course">
                    <el-button 
                        type="primary"
                        @click="goAddCourse" 
                        icon="el-icon-plus"
                        class="add-course-button">新增课程
                    </el-button>
                    <el-button 
                        type="primary"
                        @click="goAddCourse" 
                        icon="el-icon-download"
                        class="add-course-button">导出课程
                    </el-button>
                </div> 
                <form class="search-course">
                    <input type="text" placeholder="输入课程名称" class="search-style">                         
                    <button class="search-button"></button>
                    
                </form>

            <el-select v-model="searCourse" filterable placeholder="请选择" size="small" >          <!--搜索课程-->
                <el-option 
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
            </el-select>
        </div>
        <course-table></course-table>                                           <!--列表组件-->
   </div>
</template>

<script>
    import CourseTable from './course-table.vue' 
export default {
 components:{
        CourseTable,
    

    },
    data(){
        return{
            activeName: 'first',
            searCourse:'',
            options:[{}]
        }     
    },
    methods:{
         handleClick(tab, event) {
        console.log(tab, event);
      },
      /**@function 跳转到新增课程页面 */
      goAddCourse(){
          this.$router.push('/home/add-course');
      },
    },
    created(){

    },
    mounted(){

    },

}

</script>


<style  scoped>
    .course-manage-header{                  /*头部样式*/
        width: 100%;
        height: 80px;
        background-color: white;
        padding-top: 23px;
        box-sizing: border-box
    }
    .add-course-button{                        /*新增课程按钮的样式*/
        float: left;   
        height: 34px;
        background-color: #5b9bfd;
        border: 1px solid #4482f5;
        line-height: 34px;
        font-size: 16px;
        width:136px;
        padding:0;
        border-radius:2px;
        margin-left:40px;
        font-family: 'Microsoft Yahei'
    }
    .el-select{                                 /*选择器的位置*/
        float: right;
         margin-right: 15px;
    }
    .search-course{                         /*搜索的样式*/
      float: right;
      width: 20%;
      margin-right:40px;
    }
    .search-style{                                      /*搜索输入框的样式*/
       background-color: #f6f9fb;
       border: 1px solid #e5e9ec;
       border-radius: 4px 0 0 4px;
       float: left;
       width: 70%;
       height: 30px;
       padding-left: 4.5%;
      
      
    }
    .search-button{                                         /*搜索按钮的样式*/
      height: 34px;
      width: 14%;
      border: 1px solid #e5e9ec;
      cursor: pointer;
      display: block;
      background: url(../../../assets/search-o.png) no-repeat;
      border-radius: 0 4px 4px 0;
      background-position: 48% 50%;

    }
    
   
</style>





