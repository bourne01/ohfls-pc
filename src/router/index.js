import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export default new VueRouter({
    routes:[
        {
            path:'/',
            redirect:'login'
        },
        {
            path:'/login',
            name:'Login',
            component:() => import('../components/user/login.vue'),
        },
        {   path:'/home',
            name:'Home',
            component:() => import('../components/home.vue'),
            children:[
                {
                    path:'add-plan',
                    name:'AddElectivePlan',
                    component:() => import('../components/election/elective-plan/add-elective-plan.vue'),
                },
                {
                    path:'election',
                    name:'ElectionManage',
                    component:() => import('../components/election/election-manage.vue'),
                    children:[
                        {
                            path:'course-manage',
                            name:'CourseManage',
                            component:() => import('../components/election/course-manage/course-manage.vue')
                        },
                        {
                            path:'elective-name-list',
                            name:'ElectiveNameList',
                            component:() => import('../components/election/elective-name-list/elective-name-list.vue')
                        },
                        {
                            path:'elective-status',
                            name:'ElectiveStatus',
                            component:() => import('../components/election/elective-status/elective-status.vue')
                        }
                    ]
                    },
                    {
                        path:'add-course',
                        name:'AddCourse',
                        component:() => import('../components/election/course-manage/add-course.vue')
                    },
                    {
                        path:'teacher',
                        name:'TeacherManage',
                        component:()=> import('../components/teacher/teacher-manage.vue')
                    },
                    {
                        path:'student',
                        name:'StudentManage',
                        component:() => import('../components/student/student-manage.vue')
                    },
                    {
                        path:'teaching-site',
                        name:'TeachingSite',
                        component:() => import('../components/teaching-site/teaching-site-manage')
                    }, 
                   
            ]
        },
         
        
    ]
})