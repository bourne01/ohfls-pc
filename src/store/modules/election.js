import{
        getCourseList,getStuElectState,
        getElectDuties,getElectResults,
        getStuAndCouRelations,        
    } from '../../api/election'
import * as type from './mutations-type'


const state = {
    courseTable:[], //获取学部列表
    studentList:[], //获取学生选课名单
    taskList:[],//学生选课任务列表
    stuCourseStatus:[],//获取学生选课状态
    electResultList:[],//选课结果
}


const getters ={

}

const mutations = {
    [type.GET_COURSE](state,params){
        state.courseTable = params;
    },
    [type.GET_STUDENTLIST](state,params){
        state.studentList = params
    },
    [type.GET_STUCOURSESTATUS](state,params){
        state.stuCourseStatus = params
    },
    [type.GET_TASKLIST](state,params){
        state.taskList = params;
    },
    [type.GET_ELECT_RESULT](state,params){
        state.electResList = params; 
    }
}

const actions = {
    //获取课程管理信息
    getCourseTable({commit},params){
        return new Promise((resolve,reject) => {
            getCourseList(params.url,params.params)
                .then(res => {
                    commit('GET_COURSE',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err => {
                    console.log(err);
                    reject(err)
                })
        })
    },
    //删除课程
    delCou({dispatch},params){
        return new Promise((resolve,reject) =>{
            delCourse(params)
                .then(res =>{
                    dispatch('getCourseTable');
                    resolve(res.data);
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })

    },
    // 获取学生选课任务名单
    getTaskList({commit},params){
        return new Promise((resolve,reject)  =>{
            getElectDuties(params.url,params.params)
                .then(res =>{
                    commit('GET_TASKLIST',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err =>{
                    console.log(err);
                    reject(err)
                })
        })
        
    },
    // 获取学生选课结果列表
    getElectResultList({commit},params){
        return new Promise((resolve,reject)  =>{
            getElectResults(params.url,params.params)
                .then(res =>{
                    commit('GET_ELECT_RESULT',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err =>{
                    console.log(err);
                    reject(err)
                })
        })
        
    },
    //获取学生选课状态
    getStuCourseStatus({commit},params){
        return new Promise((resolve,reject) => {
            getStuElectState(params)
                .then(res =>{
                    commit('GET_STUCOURSESTATUS',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err =>{
                    console.log(err);
                    reject(err)
                })
        })
    },
    //获取学生与课程关系
    getStuCouRelations({commit},params){
        return new Promise((resolve,reject) => {
            getStuAndCouRelations(params)
                .then(res =>{
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
