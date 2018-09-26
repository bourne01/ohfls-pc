<template>
    <article class="course-wrapper"> 
        <section>
            <header>课程消息</header>                  <!--新增课程页面-->
            <div>
                <label for=""><!-- <span>*</span> -->课程封面</label>
                <el-upload
                    ref="upload"
                    action="https://jsonplaceholder.typicode.com/posts/"                    
                    class="upload-course-cover"
                    :auto-upload="false"
                    :on-change="previewImage"
                    :on-success="onSuccess"
                    :before-upload="onBeforeLoad">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>            
                </el-upload>
            </div>
            <div class="flex">
                <div class="course-name">
                    <label for=""><span>*</span>课程名称</label>
                    <el-input placeholder="必填" v-model="course.name"></el-input>
                </div>
                <div class="course-id">
                    <label for=""><span>*</span>课程编号</label>
                    <el-input placeholder="必填" v-model="course.NO"></el-input>
                </div>
            </div>
            <div class="flex">
                <div class="teaching-site">
                    <label for=""><span>*</span>选课计划</label>
                    <el-select v-model="course.plan">
                        <el-option
                            v-for="item in planList"
                            :key="item.xkpId"
                            :label="item.planName"
                            :value="item.xkpId">
                        </el-option>
                    </el-select>
                </div>
                <div class="course-type">
                    <label for="">课程类别</label>
                    <el-select v-model="course.type">
                        <el-option
                            v-for="item in courseCategoryList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </div>            
            </div>  
            <div class="flex">
                <div class="teaching-site">
                    <label for="">教学场地</label>
                    <el-select v-model="course.site">
                        <el-option
                            v-for="item in siteList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </div>          
            </div>  
            <div class="course-intro">
                <label for="">课程简介</label>
                <el-input type="textarea" v-model="course.memo"></el-input>
            </div>
            <div class="flex add-teacher-wrapper">
                <div class="add-teacher">
                    <label for=""><span>*</span>添加教师</label>                    
                    <div class="teacher-wrapper">
                        <div class="teacher-avatar" v-for="(item,idx) in teacherTags" :key="idx">
                            <img :src="require('../../../assets/teacher-avatar.jpg')" alt=""
                            @click="onCloseTag(item,4)">
                            <span>{{item.name}}</span>
                        </div>
                        <el-button icon="el-icon-plus" circle @click="onClickBtn(4)"></el-button>                       
                    </div>
                </div>
                <div class="add-org">
                    <label for=""><span>*</span>添加机构</label>
                    <div class="org-wrapper">
                        <div class="org-avatar" v-for="idx in 0" :key="idx">
                            <img :src="require('../../../assets/teacher-avatar.jpg')" alt="">
                            <span>张老师</span>
                        </div>
                        <el-button icon="el-icon-plus"></el-button>
                    </div>
                    
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
                            v-for="tag in termTags"
                            :key="tag.name"
                            closable
                            :type="tag.type"
                            @close="onCloseTag(tag,1)">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div>
                <label for=""><!-- <span>*</span> -->设置年级</label>
                <div class="add-grade">
                    <el-button @click="onClickBtn(2)">添加年级</el-button>
                    <div>
                        <el-tag
                            v-for="tag in gradeTags"
                            :key="tag.name"
                            closable
                            :type="tag.type"
                            @close="onCloseTag(tag,2)">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div>
                <label for=""><!-- <span>*</span> -->设置班级</label>
                <div class="add-class">
                    <el-button @click="onClickBtn(3)">添加班级</el-button>
                    <div>
                        <el-tag
                            v-for="tag in classTags"
                            :key="tag.name"
                            closable
                            :type="tag.type"
                            @close="onCloseTag(tag,3)">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </div>
            </div>
            <div class="flex limit">
                <div>
                    <label for=""><span>*</span>人数上限</label>
                    <el-input v-model="course.limit" placeholder="必填"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->各班限额</label>
                    <el-input v-model="course.limitPerClass"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->男生上限</label>
                    <el-input v-model="course.boyLimit"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->女生上限</label>
                    <el-input v-model="course.girlLimit"></el-input>
                </div>                
            </div>
            <div class="flex ratio">
                <div>
                    <label for=""><!-- <span>*</span> -->特优比率(%)</label>
                    <el-input v-model="course.ultimateRatio"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->优秀比率(%)</label>
                    <el-input v-model="course.excellentRatio"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->良好比率(%)</label>
                    <el-input v-model="course.goodRatio"></el-input>
                </div>
            </div>
            <div class="flex credit">
                <div>
                    <label for=""><!-- <span>*</span> -->特优学分</label>
                    <el-input v-model="course.ultimateCredit"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->优秀学分</label>
                    <el-input v-model="course.excellentCredit"></el-input>
                </div>
                <div>
                    <label for=""><!-- <span>*</span> -->良好学分</label>
                    <el-input v-model="course.goodCredit"></el-input>
                </div>
            </div>
            <div class="lowest-credit">
                <label for=""><!-- <span>*</span> -->选本课最低学分</label>
                <el-input v-model="course.lowestCredit"></el-input>
            </div>
            <div class="save">
                <el-button @click="submit">{{actionName}}</el-button>
            </div>    
        </section>
        <my-dialog 
            :is-pop="isPop" 
            :type="type" 
            @dialog-close="onDialogClose"
            :current-list="curTags"
            @selected="onDialogConfirm"></my-dialog>   
    </article>
</template>

<script>
import MyDialog from './my-dialog'
import { mapState } from 'vuex';
import { getSelector } from '../../../api/public';
import { xhrErrHandler } from '../../../utils/util';
import { addCourse } from '../../../api/election';
import { getTeachers } from '../../../api/teacher'
export default {
    components:{
        MyDialog,
    },
    data() {
      return {
        params:{},
        course:{//课程对象，一下内容为默认值
            type:2,//默认普通课程
            ultimateRatio:30,
            excellentRatio:60,
            goodRatio:10,
            ultimateCredit:3,
            excellentCredit:2,
            goodCredit:1,
            lowestCredit:0,
            parentCourseId:undefined,
        },
        isNewCourse:true,//判断当前是否新建，true为新建，fasle为编辑
        isPop:false,//是否打开弹窗
        type:-1,//弹窗类型，type的值：1添加学期、2添加年级、3添加班级，4添加教师、-1默认
        curTags:'',//当前被打开弹窗的已有对象列表
        imageUrl:'',
        termTags:[],//授课学期名称
        classTags:[],//授课班级名称
        gradeTags:[],//授课年级名称
        teacherTags:[],//授课教师名称
        termList:[],//系统中的学期列表
        gradeList:[],//系统中的年级列表
        classList:[],//系统中的班级列表
        actionName:'保存并返回',//操作类型（"保存并返回","修改并返回"）
        siteList:[],//教学场地列表
        courseCategoryList:[],//课程类型列表，2普通(默认) 4自修研习
      };
      
    },
    computed:{
        ...mapState({
            planList:state => state.election.planList,
            courseList:state => state.election.courseTable
        })
    },
    methods: {
        /**
         * @function 监听添加学期、年级和班级按钮事件，打开相应的对话框 
         * @param {1为添加学期，2为添加年级，3为添加班级} type
         */
        onClickBtn(type){            
            switch(type){
                case 1://学期
                    this.curTags = JSON.stringify(this.termTags);
                    break;
                case 2://年级
                    this.curTags = JSON.stringify(this.gradeTags);
                    break;
                case 3://班级
                    this.curTags = JSON.stringify(this.classTags);
                    break;
                case 4://教师
                    this.curTags = JSON.stringify(this.teacherTags);
                    break;
                default:
                    this.$message({
                        type:'error',
                        message:'设置对象列表出错！'
                    })
            }
            this.isPop = true;
            this.type = type;
        },

        /**@function 监听对话框关闭事件 */
        onDialogClose(){
            this.isPop = false;
            this.type = -1;//初始化
            console.log('hi...')
        },

        /**
         * @function 监听点击删除Tag事件 
         * @param {tag对象} tag
         * @param {当前tag类型(1学期、2年级、3班级、4教师)} type
         */
        onCloseTag(tag,type){
            let i = 0;
            switch(type){                
                case 1://学期
                    for(let item of this.termTags){
                        if(item.id == tag.id){
                            this.termTags.splice(i,1);
                            break;
                        }
                        i++;
                    }
                    break;
                case 2://年级
                    for(let item of this.gradeTags){
                        if(item.id == tag.id){
                            this.gradeTags.splice(i,1);
                            break;
                        }
                        i++;
                    }
                    break;
                case 3://班级
                    for(let item of this.classTags){
                        if(item.id == tag.id){
                            this.classTags.splice(i,1);
                            break;
                        }
                        i++;
                    }
                    break;
                case 4://教师
                    for(let item of this.teacherTags){
                        if(item.id == tag.id){
                            this.teacherTags.splice(i,1);
                            break;
                        }
                        i++;
                    }
                    break;
                default:
                    this.$message({
                        type:'error',
                        message:'删除对象列表出错！'
                    })
            }
        },

        /**
         * @function 监听对话框确认事件 
         * @param {已经选择的对象列表JSON字符串，来自子组件‘my-dialog’} selectedList
         * @param {对话框类型(1添加学期、2添加年级、3添加班级),来自子组件‘my-dialog’} type
         */
        onDialogConfirm(selectedList,type){
            switch(type){
                case 1://学期
                    this.termTags = JSON.parse(selectedList);
                    break;
                case 2://年级
                    this.gradeTags = JSON.parse(selectedList);
                    break;
                case 3://班级
                    this.classTags = JSON.parse(selectedList);
                    break;
                case 4://教师
                    this.teacherTags = JSON.parse(selectedList);
                    break;
                default:
                    this.$message({
                        type:'error',
                        message:'对象列表添加出错！'
                    })
            }
        },
        onDialogClose(){
            this.isPop = false;
            this.type = -1;//初始化
            console.log('hi...')
        },

        /**@function 预览图片 */
        previewImage (file, fileList) {
            let fileName = file.name;
            let regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
            if (regex.test(fileName.toLowerCase())) {
                this.imageUrl = file.url;
            } else {
                this.$message.error('请选择图片文件');
            }
        },


        /**@function 提交课程信息 */
        submit(){            
            this.$refs.upload.submit();
        },
        
        /**
         * @function 获取给定的对象列表中对象id属性的值 
         * @param {对象列表} objList
         * @returns {返回以','隔开的字符串}
         */
        getIds(objList){
            let _objList = [];
            for(let item of objList){
                _objList.push(item.id);
            }
            return _objList.toString();
        },

        /**@function 检查输入项是否合法 */
        checkInputValue(){
            let isValid = true;//默认所有输入项都有效，false则表示无效
             /**@function 检查必填项，是否已经都有内容，如果为空，则提示用户输入相应内容 */
            if(!this.course.name){
                this.$message({
                    type:'warning',
                    message:'课程名称不能为空！'
                });
                isValid = false;
            }else if(!this.course.NO){
                this.$message({
                    type:'warning',
                    message:'课程编号不能为空！'
                });
                isValid = false;
            }else if(!this.course.plan){
                this.$message({
                    type:'warning',
                    message:'选课计划不能为空！'
                });
                isValid = false;
            }else if(!this.course.limit){
                this.$message({
                    type:'warning',
                    message:'学生总人数限制不能为空！'
                });
                isValid = false;
            }
            /**@function 特优、优秀和良好三者比例之和不能超过100% */
            if((this.course.ultimateRatio/100 + this.course.excellentRatio/100 + this.course.goodRatio/100) > 1){
                this.$message({
                    type:'warning',
                    message:'特优、优秀和良好三者比例之和不能超过100%'
                })
                isValid = false;
            }

            if(!isValid)//检查结果无效
                return true;
            else{//检查结果有效
                return false;
            }
        },

        /**@function 在点击提交按钮后，文件上传之前，检查表单元素是否有效，
         * 然后把文件与其它参数合二为一，传递给接口函数
         * @param {要上传的文件对象} file
         */
        onBeforeLoad(file){      
            //this.checkInputValue();           
            let formData = new FormData();
            formData.append('file',file);
            formData.append('xkpId',this.course.plan);
            formData.append('couNO',this.course.NO)
            formData.append('couType',this.course.Type)
            formData.append('couName',this.course.name)
            formData.append('romId',this.course.site)
            formData.append('couExplain',this.course.memo)
            formData.append('credit1',this.course.ultimateCredit)
            formData.append('credit2',this.course.excellentCredit)
            formData.append('credit3',this.course.goodCredit)
            formData.append('lvRate1',this.course.ultimateRatio/100)
            formData.append('lvRate2',this.course.excellentRatio/100)
            formData.append('lvRate3',this.course.goodRatio/100)
            formData.append('creditMin',this.course.lowestCredit)
            formData.append('manMax',this.course.limit)
            formData.append('girlMax',this.course.girlLimit)
            formData.append('boyMax',this.course.boyLimit)
            formData.append('couThrIds',this.getIds(this.teacherTags))
            formData.append('termIds',this.getIds(this.termTags))
            formData.append('claIds',this.getIds(this.classTags))
            formData.append('gradeIds',this.getIds(this.gradeTags))

            addCourse(formData)
                .then(res => {
                    if(res.data.success){
                        this.$message({
                            type:'info',
                            message:'课程创建成功！'
                        })
                    }else{
                        if(res.data.type === 1){
                                this.$message({
                                type:'error',
                                message:res.data.message
                            });
                        }else{
                            console.log(res.data.message);
                            this.$message({
                                type:'error',
                                message:'发生未知错误，请联系管理员'
                            })
                        }
                        
                    }                    
                })
                .catch(err => {
                    xhrErrHandler(err,this.$message,this.$router)
                })
        },

        /**
         * @function 根据Id查询名称
         * @param {id字符列表，以','隔开} id字符串
         * @param {对象类型} type
         * @returns {对象（{name:'name',id:'id'...}）列表} 返回对象列表
         */
        getListById(type,idList){
            if(!idList)return;
            else{
                idList = idList.split(',');
            }
            let objList = [];
            let params = {};
            switch(type){
                case 'Term':
                    params = {
                        f:'uxTerm',
                        simple:0
                    };                    
                    break;               
                case 'Grade':
                    params = {
                        f:'uxCode',
                        codeType:35,
                        simple:0
                    };
                    break;
                case 'Class':
                    params = {
                        f:'uxTerm',
                        simple:0
                    };
                    break;
                 case 'Teacher':
                    
                    break;
            }
            if(type != 'Teacher'){
                getSelector(params)
                        .then(res => {
                            idList.forEach(element => {
                                for(let item of res.data.dataList){
                                    if(element == item.id){
                                        objList.push(item)
                                    }
                                }    
                            });
                            return objList;
                        })
            }else{
                getTeachers('thr!query.action',{})
                .then(res => {
                    if(res.data.success){
                        idList.forEach(ele => {
                            for(let item of res.data.dataList){
                                if(ele == item.thrId){
                                    objList.push({name:item.thrName,id:item.thrId})
                                }
                                
                            }
                            return objList;
                        })
                            
                    }else{
                        this.$message({
                            type:'error',
                            message:res.data.message
                        })
                    }
                })
                .catch(err => {
                    xhrErrHandler(err,this.$router,this.$message)
                })
            }
            //console.log(objList)
            
        },       


        /**@function 文件上传成功后 */
        onSuccess(res,file,fileList){
            
            this.imageUrl = res.url;
        },
    },
    mounted(){
        /**@function 获取上课地点 */
        let params = {
                f:'uxRom',
                /* state:2, */
                simple:0
            };
        getSelector(params)
            .then(res => {
                this.siteList = res.data.dataList;
            })
            .catch(err => {
                xhrErrHandler(err,this.$message,this.$router)
            })
        /**@function 获取课程类型(2普通课程,4为自修课程) */
        params = {
                f:'uxCode',
                /* state:2, */
                codeType:11,
                simple:0
            };
        getSelector(params)
            .then(res => {
                this.courseCategoryList = res.data.dataList;
            })
            .catch(err => {
                xhrErrHandler(err,this.$message,this.$router)
            })
        /**@function 当前是否来自新建课程还是编辑课程 */
        let courseId = this.$route.query.courseId;
        if(courseId){
            this.isNewCourse = false;
            for(let course of this.courseList){
                if(course.selCouId === courseId){
                    this.course = {
                       plan:course.xkpId,
                       NO:course.couNO,
                       name:course.couName,
                       type:course.couType,
                       site:course.romId,
                       memo:course.couExplain,
                       ultimateCredit:course.credit1,
                       excellentCredit:course.excellentCredit,
                       goodCredit:course.goodCredit,
                       ultimateRatio:course.lvRate1,
                       excellentRatio:course.lvRate2,
                       goodRatio:course.lvRate3,
                       lowestCredit:course.creditMin,
                       limit:course.manMax,
                       girlLimit:course.girlMax,
                       boyLimit:course.boxMax,
                    }
                    this.termTags = this.getListById('Term',course.termIds);
                    console.log('term-------------------')
                    console.log(this.termTags)
                    this.gradeTags = this.getListById('Grade',course.grades);
                    this.classTags = this.getListById('Class',course.claIds);
                    this.teacherTags = this.getListById('Teacher',course.couThrIds);
                }
            }
        }
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
    /* .teacher-wrapper .el-icon-plus{
       font-size:24px;
   } */
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
        /* justify-content:space-between; */
    }
    .teacher-wrapper,
    .org-wrapper{
        display:flex;
        width:376px;
        box-sizing: border-box;
        padding-left: 6px;
        flex-flow: wrap;
    }
    .teacher-wrapper .el-button.is-circle,
    .org-wrapper .el-button{
        height:50px;
        width:50px;
        padding:0;
        font-size:24px;
    }

    .teacher-avatar,
    .org-avatar{
        text-align:center;
        width:50px;
        margin-right:15px;  
        margin-bottom:10px;    
        
    }
    .teacher-avatar img{
        height:50px;
        width:50px;
        border-radius:50%;
        margin-bottom:5px;
    }
    .org-avatar img{
        height:50px;
        width:50px;
        border-radius:4px;
        margin-bottom:5px;
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
        margin-bottom:10px;
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

















