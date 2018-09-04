<template>
    <article class="course-wrapper"> 
        <section>
            <header>课程消息</header>                  <!--新增课程页面-->
            <div>
                <label for=""><span>*</span>课程封面</label>
                <el-upload
                    action="https://jsonplaceholder.typicode.com/posts/"                    
                    class="upload-course-cover"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            
                </el-upload>
            </div>
            <div class="flex">
                <div class="course-name">
                    <label for=""><span>*</span>课程名称</label>
                    <el-input placeholder="输入名称" v-model="course.name"></el-input>
                </div>
                <div class="course-id">
                    <label for=""><span>*</span>课程编号</label>
                    <el-input placeholder="输入编号" v-model="course.id"></el-input>
                </div>
            </div>
            <div class="flex">
                <div class="teaching-site">
                    <label for="">教学场地</label>
                    <el-select v-model="course.site"></el-select>
                </div>
                <div class="course-type">
                    <label for="">课程类别</label>
                    <el-select v-model="course.type"></el-select>
                </div>            
            </div>  
            <div class="course-intro">
                <label for="">课程简介</label>
                <el-input type="textarea" v-model="course.memo"></el-input>
            </div>
            <div class="flex add-teacher-wrapper">
                <div class="add-teacher">
                    <label for=""><span>*</span>添加教师</label>
                    <el-upload
                        action="https://jsonplaceholder.typicode.com/posts/"
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                        </el-upload>
                    <!-- <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog> -->
                </div>
                <div class="add-org">
                    <label for=""><span>*</span>添加机构</label>
                    <el-upload
                        action="https://jsonplaceholder.typicode.com/posts/"
                        list-type="picture-card"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                        </el-upload>
                    <!-- <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog> -->
                </div>
            </div>            
        </section>           
        <section class="course-setting">
            <header>课程设置</header>
            <div>
                <label for=""><span>*</span>设置学期</label>
                <div class="add-term">
                    <el-button @click="onClickBtn(1)">添加学期</el-button>
                    <div>
                        <el-tag
                            v-for="tag in tags"
                            :key="tag.name"
                            closable
                            :type="tag.type">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div>
                <label for=""><span>*</span>设置年级</label>
                <div class="add-grade">
                    <el-button @click="onClickBtn(2)">添加年级</el-button>
                    <div>
                        <el-tag
                            v-for="tag in tags"
                            :key="tag.name"
                            closable
                            :type="tag.type">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div>
                <label for=""><span>*</span>设置班级</label>
                <div class="add-class">
                    <el-button @click="onClickBtn(3)">添加班级</el-button>
                    <div>
                        <el-tag
                            v-for="tag in tags"
                            :key="tag.name"
                            closable
                            :type="tag.type">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div class="flex limit">
                <div>
                    <label for=""><span>*</span>人数上限</label>
                    <el-input v-model="course.limit"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>各班限额</label>
                    <el-input v-model="course.limitPerClass"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>男生上限</label>
                    <el-input v-model="course.manLimit"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>女生上限</label>
                    <el-input v-model="course.girlLimit"></el-input>
                </div>                
            </div>
            <div class="flex ratio">
                <div>
                    <label for=""><span>*</span>特优比率(%)</label>
                    <el-input v-model="course.ultimateRatio"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>优秀比率(%)</label>
                    <el-input v-model="course.excellentRatio"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>良好比率(%)</label>
                    <el-input v-model="course.goodRatio"></el-input>
                </div>
            </div>
            <div class="flex credit">
                <div>
                    <label for=""><span>*</span>特优学分</label>
                    <el-input v-model="course.ultimateCredit"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>优秀学分</label>
                    <el-input v-model="course.excellentCredit"></el-input>
                </div>
                <div>
                    <label for=""><span>*</span>良好学分</label>
                    <el-input v-model="course.goodCredit"></el-input>
                </div>
            </div>
            <div class="lowest-credit">
                <label for=""><span>*</span>选本课最低学分</label>
                <el-input v-model="course.lowestCredit"></el-input>
            </div>
            <div class="save">
                <el-button @click="submit">保存并返回</el-button>
            </div>    
        </section>
        <my-dialog :is-pop="isPop" :type="type" @dialog-close="onDialogClose"></my-dialog>   
    </article>
</template>

<script>
import MyDialog from './my-dialog'
export default {
    components:{
        MyDialog,
    },
    data() {
      return {
        course:{},
        isPop:false,//是否打开弹窗
        type:-1,//弹窗类型，type的值：1添加学期、2添加年级、3添加班级
        tags: [
                { name: '标签一', type: '' },
                { name: '标签二', type: 'success' },
                { name: '标签三', type: 'info' },
                { name: '标签四', type: 'warning' },
                { name: '标签五', type: 'danger' }],
        imageUrl:''
      };
      
    },
    methods: {
        /**
         * @function 监听添加学期、年级和班级按钮事件，打开相应的对话框 
         * @param {1为添加学期，2为添加年级，3为添加班级} type
         */
        onClickBtn(type){
            this.isPop = true;
            this.type = type;
        },
        /**@function 添加对话框关闭事件 */
        onDialogClose(){
            this.isPop = false;
            console.log('hi...')
        },
        /**@function 提交课程信息 */
        submit(){

        },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handlePictureCardPreview(){},
      beforeAvatarUpload(){

      },
      handleAvatarSuccess(){},
    }
  }
</script>

<style>
    .upload-course-cover .el-upload{
        width:393px;
        height:239px;
        line-height:239px;
        border: 1px dashed #e5e9ec;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        background-color:#f6f9fb;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
    }
    .course-wrapper .el-input__inner{
        background-color:#f6f9fb;
    }
    .course-wrapper .el-textarea__inner{
        background-color:#f6f9fb;
        min-height:88px!important;
    }
</style>


<style scoped>    
    .course-wrapper section{
        padding-top:50px;
        background-color:#fff;
        text-align:left;
        padding-bottom:10px;
    }
    section.course-setting{
        margin-top:10px;
    }
    .course-wrapper section>header{
        padding-left:70px;
        margin-bottom:40px;
        font-size: 24px;
        font-family: 'MicrosoftYaHei';
        color:#1a1a1f;
    }
    .course-wrapper section>div{
        padding-left:70px;
        margin-bottom:40px;
    }   
    
    .flex{
        display:flex;
    }
    label{
        display:block;
        margin-bottom:10px;
    }
    label>span{
        color:red;
        margin-right:5px;
    }
   .course-name .el-input,
   .teaching-site .el-select{
       width:392px;
       margin-right:38px;
   }
   .course-id .el-input,
   .course-type .el-select{
       width:320px;
   }
   .course-intro .el-textarea{
       width:752px;
       /* min-height:88px!important; */
   }
   .add-teacher-wrapper{
       width:752px;
       justify-content:space-between;
   }
    .add-term{
        width:752px;
        min-height:133px;
        border:1px solid #eff2f4;
        border-radius:2px;
        padding:20px;
        box-sizing:border-box;
    }
    .add-term .el-button,
    .add-grade .el-button,
    .add-class .el-button{
        padding:0;
        height:32px;
        line-height:32px;
        width:112px;
        background-color:#5b9bfd;
        color:#fff;
        margin-bottom:20px;
    }
    .el-tag{
        margin-right:20px;
    }
    .add-grade,.add-class{
        width:752px;
        min-height:150px;
        border:1px solid #eff2f4;
        border-radius:2px;
        padding:20px;
        box-sizing:border-box;
    }
    .limit,.ratio,.credit{
        justify-content:space-between;   
        width:752px;     
    }
    .limit .el-input{
        width:150px;
    }
    .ration .el-input,
    .credit .el-input{
        width:200px;
    }
    .lowest-credit .el-input{
        width:200px;
    }
   .save .el-button{               /*保存并返回按钮的样式*/
        width: 172px;
        height: 44px;
        font-size: 16px;
        line-height:44px;
        background-color: #0d714a;
        border: none;
        color:#fff;
        padding:0;
        margin-top:70px;
    }
</style>

















