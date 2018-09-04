<template>
  <div class="user-input">
      <input type="text" name="username" v-model="username" class="username" @change="onBlur" placeholder="请输入用户名">
      <img :src="require('../../assets/Sign in_User name.png')" alt="" class="login">
  </div>
</template>

<script>
export default {
    data(){
        return{
            username:'',
        }
    },
    methods:{
        onBlur(){
            let password = this.$store.state.user.user.password;
            this.$store.commit('user/SET_USER',{account:this.username,password})
        }
    },   
    created(){

    }
}
</script>

<style scoped>
    .username{
        border-radius: 5px;
        height: 58px;
        line-height: 58px;
        font-size:18px;
        border:none;
        width:310px;
        padding-left:70px;
        outline:none;
    }
    .user-input{
        position: relative;
        margin-bottom:30px;
    }
    .login{
        width:31px;
        position: absolute;
        left:23px;
        top:13px
    }
</style>


