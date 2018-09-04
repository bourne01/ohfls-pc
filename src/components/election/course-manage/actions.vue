<template>
    <div>
        <el-button type="text" size="small" class="edit" @click="edit()" >编辑</el-button>
        <el-button type="text" size="small">添加子课程</el-button>
        <el-button type="text" 
                 size="mini"
                  @click="del()"
                  >                                                        
                  <img :src="require('../../../assets/delete-oc.png')" alt=""  >
         </el-button>
         <sub-course-popup :sub-course-popup="scPopup"></sub-course-popup>
    </div>
</template>

<script>
import { mapActions } from 'vuex';
import SubCoursePopup from './sub-course/sub-course-popup'
export default {
    props:['current-row'],
    components:{
        SubCoursePopup
    },
    data(){
        return{
           scPopup:{isPop:false},
           openSubCourse:false
        }
    },
    methods:{
        ...mapActions('election',["delCourse"]),
        /**
         * @function 监听打开弹窗
         */
        edit(){
            this.scPopup.isPop = true;//打开弹窗
        },
        /**
         *@function 监听删除事件
         */
        del(index, rows){            
            this.$confirm('确认删除该课程吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
               this.$store.dispatch('election/delCourse',params)
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
        
    }
    
    
    
}
</script>

<style scoped>
</style>
