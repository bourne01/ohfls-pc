<template>
    <div>
        <el-button type="text" size="small" class="edit" @click="edit" >编辑</el-button>
        <el-button type="text" size="small" @click="addSubCourse">添加子课程</el-button>
        <el-button type="text" size="mini"  @click="deleteCourse" icon="el-icon-delete"></el-button>
         <sub-course-popup :sub-course-popup="scPopup"></sub-course-popup>
    </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import SubCoursePopup from './sub-course/sub-course-popup'
import { xhrErrHandler } from '../../../utils/util';
import { changeCouState } from '../../../api/election';
export default {
    props:['current-course'],
    components:{
        SubCoursePopup
    },
    data(){
        return{
           scPopup:{isPop:false},
           openSubCourse:false
        }
    },
    computed:{
        ...mapState('election',{
            courseList:state => state.courseTable
        })
    },
    methods:{
        ...mapActions('election',["removeCourse"]),
        /**
         * @function 监听点击编辑按钮事件，跳转到编辑页面
         */
        edit(){
            //this.scPopup.isPop = true;//打开弹窗
            this.$router.push({
                name:'AddCourse',
                query:{courseId:this.currentCourse.selCouId}
            })
        },

        /**
         * @function 监听添加子课程事件，跳转到新增课程页面
         */
        addSubCourse(){
            this.$router.push({
                name:'AddCourse',
                query:{parentCourseId:this.currentCourse.selCouId}
            })
        },

        /**
         * @function 监听删除课程事件,然后根据Id删掉所选课程
         */
        deleteCourse(){   
            let courseId = this.currentCourse.selCouId;         
            this.$confirm('确认删除该课程吗?', '提示', {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning'})
                .then(() => {
                    /**
                     * 不能删除课程状态是“2开启，3关闭”这2种状态的课程（ 4确认 5完成）
                     */
                    /* if(this.currentCourse.couState == 2 || this.currentCourse.couState == 3){ */
                    changeCouState({selCouIds:courseId,couState:5})
                        .then(res => {
                            if(res.data.success){
                                this.removeCourse({selCouIds:courseId})
                                    .then(res => {
                                        if(res.success){
                                            this.$message({
                                                    type: 'success',
                                                    message: res.message
                                                });
                                                this.$emit('update-course-list');
                                        }else{
                                            if(res.type == 1){
                                                this.$message({
                                                    type:'error',
                                                    message:res.message
                                                })
                                            }else{
                                                this.$message({
                                                    type:'error',
                                                    message:'发生未知错误，请联系管理员！'
                                                })
                                                console.log(res.message)
                                            }
                                        }                        
                                    })
                                    .catch(err => {
                                        xhrErrHandler(err,this.$message,this.$router)
                                    })
                            }else{
                                if(res.data.type == 1){
                                    this.$message({
                                        type:'error',
                                        message:res.message
                                    })
                                }else{
                                    this.$message({
                                        type:'error',
                                        message:'发生未知错误，请联系管理员！'
                                    })
                                    console.log(res.message)
                                }
                            }
                        })
                    /* } */
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                    });
        },
        
    }
    
    
    
}
</script>


<style>
    .el-icon-delete{
        color: #ff7a7b;
        font-size: 14px;
    }
</style>

<style scoped>
    
</style>
