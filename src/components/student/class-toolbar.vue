<template>
    <div>
        <div class="class-toolbar">
            <div>
                <el-button icon="el-icon-plus" size="small" @click="isGradePop=true">设置年级</el-button>
                <el-button icon="el-icon-plus" size="small" @click="isClassPop=true">新增班级</el-button>
            </div>
            
            <!-- <el-select>

            </el-select> -->
            <el-input placeholder="请输入班级">
                <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
        </div>
        <grade-popup :is-pop="isGradePop" @close-pop="isGradePop=false"></grade-popup>
        <class-popup 
            :is-pop="isClassPop" 
            @close-pop="isClassPop=false"
            :class-id="classId"></class-popup>
    </div>
    
</template>

<script>
import GradePopup from './add-grade-popup'
import ClassPopup from './add-class-popup'
export default {
    components:{
        GradePopup,
        ClassPopup,
    },
    data(){
        return{
            isGradePop:false,
            isClassPop:false,
            classId:'',
        }
    },
    mounted(){
        this.$root.bus.$on('class-id',(classId) => {
            this.classId = classId;
            this.isClassPop = true;
        });
    },
    destroyed(){
        this.$root.bus.$off('class-id');
    }
    
}
</script>

<style>
    .class-toolbar .el-input__inner{
        height:32px;
        line-height:32px;
    }
    .class-toolbar .el-input__icon{
        line-height: 32px;
    }
    .class-toolbar .el-input-group__append{
        padding:0 10px;
    }
</style>


<style scoped>
    .class-toolbar{
        padding-left:20px;
        display: flex;
        justify-content: space-between;
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
        /* margin-right:10px; */
    }
    .el-input-group,.el-select{
        width:160px;
    }
</style>

