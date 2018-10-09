import {getClassStudents,getClasses} from '../../api/student'
import * as type from './mutations-type'



const state = {
    gradeTable:[], //获取年级信息
    classTable:[]//获取学生信息
}

const getters ={

}

const mutations ={
    [type.GET_GRADE](state,params){
        state.gradeTable = params
    },
    [type.GET_CLASSSTUDENT](state,params){
        state.classTable = params
    }
}


const actions = {
    //获取年级信息
   getClassTable({commit},params){
       return new Promise((resolve,reject)=>{
        getClasses(params)
            .then(res =>{
                commit('GET_GRADE',res.data.dataList);
                resolve(res.data)
            })
            .catch(err =>{
                console.log(err);
                reject(err)
            }) 
       })
   },
   //获取班级学生信息
   getClassStudentList({commit},params){
       return new Promise((resolve,reject)=>{
        getClassStudents(params.url,params.params)
            .then(res =>{
                commit('GET_CLASSSTUDENT',res.data.dataList);
                resolve(res.data);
            })
            .catch(err =>{
                console.log(err);
                reject(err)
            })
       })
   }
}


export default {
    namespaced:true,
    state,     
    getters,     
    mutations,     
    actions 
}