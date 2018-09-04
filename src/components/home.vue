<template>
    <div class="home">
        <el-container>
            <el-header> 
                <img :src="require('../assets/logo.png')" alt="" width="300" class="logo">           <!--这里是logo-->
                <div class="user-area">                          <!--这一整块显示的是右上角的用户一块-->
                    <div class="user-avatar"></div>             <!--这个是头像-->
                  <a class="name-style" href="#">{{teacherName}}</a>         <!--这个是用户名（具体哪位老师或者管理员）-->
                  <el-badge :value="5" class="item">
                      <img :src="require('../assets/news-oc.png')" alt="" class="news-oc" >             <!--这个是消息-->
                   </el-badge>    
                     <img :src="require('../assets/Close-oc.png')" alt="" class="close-oc">         <!--这个是登出-->
                </div>
                </el-header>                    <!--头部结束-->
                <el-row class="tac">
                <el-col :span="2">
                    <el-aside>          <!--这个是侧边栏-->
                        <el-menu
                            default-active= "1"
                            background-color="#2b2b32"
                            text-color="#b5b5c0"
                            active-text-color="#fff"
                            >                        <!--初始化一个菜单列表-->
                            <router-link to="/home/election">
                                <el-menu-item index="1" 
                                class="election-admin"
                                :class="{election:index===1}" 
                                @click="index=1">                                                                    <!--选课管理选项卡--> 
                                    <span slot="title" >选课管理</span>
                                    <span class="caret-left"></span>      
                                </el-menu-item>
                            </router-link>
                            <router-link to="/home/teacher">
                                <el-menu-item index="2" 
                                class="teacher-admin" 
                                :class="{teacher:index===2}"
                                @click="index=2">                                                                            <!--教师管理选项卡-->
                                    <span slot="title">教师管理</span>
                                    <span class="caret-left"></span>      
                                </el-menu-item>
                             </router-link>  
                            <router-link to="/home/student">                                                                 
                                <el-menu-item index="3" 
                                class="student-admin" 
                                :class="{student:index===3}" 
                                @click="index=3">                                                                             <!--学生管理选项卡-->
                                    <span slot="title">学生管理</span>
                                    <span class="caret-left"></span>      
                                </el-menu-item>
                            </router-link> 
                            <!-- <router-link to="/home/social-org"> -->
                                <el-menu-item index="4" 
                                class="social"
                                :class="{sociaL:index===4}"
                                @click="index=4">                                                                                 <!--社会机构选项卡-->
                                    <span slot="title">社会机构</span>
                                    <span class="caret-left"></span>      
                                </el-menu-item>
                            <!-- </router-link> -->
                            <router-link to="/home/teaching-site">
                                <el-menu-item index="5"
                                class="teach-area" 
                                :class="{teach:index===5}" 
                                @click="index=5">                                                                            <!--教学场地选项卡-->                                   
                                    <span slot="title">教学场地</span>
                                    <span class="caret-left"></span>      
                                </el-menu-item>
                            </router-link>
                        </el-menu>
                             
                    </el-aside>
                  </el-col> 
                <el-col :span="22">
                    <el-main>
                        <transition name="fade" mode="out-in">
                            <router-view></router-view>
                        </transition>
                    </el-main>
                </el-col>
            </el-row>            
        </el-container>
    </div>
</template>

<script>
export default {
    components:{    
    },
    data(){
        return{
            teacherName:'韩老师',
            index:''
        }   
    },
    methods:{

    },
    created(){

    },
    mounted(){

    }
}
</script>

<style scoped>
    .home,.el-container{
        height:100%;        
    }
    .el-row{
        height:calc(100% - 100px);
    }
    .el-col-2,.el-col-22{
        height:100%;
    }
    .el-header{                             /*头部的整体样式*/
        background-color: #edf2f6;
        color: #333;
        text-align: center;
        height:100px!important;
        padding-top: 22px;
        padding-right:40px;
    }
    
    .el-aside {                             /*侧边栏的整体样式*/
        background-color: #2b2b32;
        color: #333;
        text-align: center;
        line-height: 200px;
        width:100%!important;
        height:100%;
        padding-top: 20px;
    }
    
    .el-main {                                            
        background-color: #E9EEF3; 
        color: #333;
        text-align: center;
        height: 100%;
        padding: 0!important
    }
    
     body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }
    
    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
    .el-menu-item{
         height:9%;
    }
    .logo{               /**定义logo的样式*/        
        float: left;       
    }
  .user-area{                   /*定义整个头部右边区域的整体样式*/
        float: right;

    }
    .user-avatar{                 /*定义头像的整体样式*/
        width: 50px;
        height: 50px;
        background-color: aqua;
        border-radius: 50%;
        border:2px solid white;
        background-image: url('../assets/user.png');
        background-size:50px;
        float: left;
        margin-right: 15px;
        margin-top: 1%;
    }
    .name-style{                   /*定义用户的名字的样式*/
        float: left;
        margin-top: 22px;
        margin-right: 50px; 
        text-decoration: none;
         color: black;
    }
  
    .item{                     /*定义消息模块的样式*/
        float: left;
        margin-right: 50px;
        margin-top: 18px;
    }
    .close-oc{                 /*定义登出的样式*/
        float: left;
        margin-top: 18px;
    }
  
    .el-menu{                   
    border: none;
    
    }

    .menu-style{                /*定义侧边栏导航菜单图片的样式*/
        display: block;
        margin: 0 auto;
        margin-bottom: -20px;
        padding-top: 20px;
    }
    .el-menu-item{              /*同意每单项导航的高度*/
        height: 104px;
    }
    .election-admin{            /*选课管理选项卡样式*/
        background: url('../assets/Course_Administration_nor.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
    
        padding-top:40px;
    }
    .teacher-admin{         /*教师管理选项卡样式*/
        background: url('../assets/Teacher_Administration_nor.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
        padding-top: 40px;
    }
    .student-admin{         /*学生管理选项卡演示*/
        background: url('../assets/Student management_notr.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
        padding-top: 40px;
    }
    .social{            /*社会机构选项卡*/
        background: url('../assets/Student_Administration_nor.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
        padding-top: 40px;
    }
    .teach-area{            /*教学场地选项卡*/
        background: url('../assets/teaching_field_nor.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
        padding-top: 40px;
    }
    .election{
        background: url('../assets/Course_Administration_sel.png') no-repeat;
        background-position: 50% 32% ;
        margin: 0 auto;
        padding-top: 40px;
    }
    .teacher{
       background: url('../assets/Teacher_Administration_sel.png') no-repeat;
       background-position: 50% 32% ;         
       margin: 0 auto;         
       padding-top: 40px;
    }
    .student{
        background: url('../assets/Student management_sel.png') no-repeat;
        background-position: 50% 32% ;        
        margin: 0 auto;         
        padding-top: 40px;
    }
    .sociaL{
        background: url('../assets/Student_Administration_sel.png') no-repeat;
        background-position: 50% 32% ;         
        margin: 0 auto;         
        padding-top: 40px;
    }
    .teach{
        background: url('../assets/teaching_field_sel.png') no-repeat;
        background-position: 50% 32% ;         
        margin: 0 auto;         
        padding-top: 40px;
    }
    .el-menu-item:hover{
        position: relative;
    }
    .caret-left{
        border-left:none;
        border-top:10px solid transparent;
        border-bottom:10px solid transparent;
        border-right:10px solid #fff;
        position: absolute;
        right:0;
        top:42px;
        display:none;
    }
    .el-menu-item:hover .caret-left{
        display:block;
    }
</style>

<style>
    .list th{       /*  表头字体居中 */
        text-align: center;
        background-color:#E9EEF3;
        color: #1a1a1f;    
  
}
 
</style>
