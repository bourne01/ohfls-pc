/** 选课任务 */

	/** 学生Id       */ private Integer   stuId    ;
	/** 选课计划Id   */ private Integer   xkpId    ;
	/** 任务开启时间 */ private Timestamp startTime;
	/** 任务关闭时间 */ private Timestamp endTime  ;
	/** 任务完成时间 */ private Timestamp overTime ;
	/** 任务状态     */ private Byte      taskState; //1未开启 2开启(默认) 3关闭 4完成
	/** 学期Id       */ private Integer   termId   ;
	/** 计划编号     */ private String    planNO   ;
	/** 计划名称     */ private String    planName ;
	/** 选课计划状态 */ private Byte      xkpState ; //1未开启 2开启(默认) 3关闭 4完成
	/** 学籍号       */ private String    stuNO    ;
	/** 学生姓名     */ private String    stuName  ;
	/** 学生性别     */ private String    stuSex   ; //男(默认)、女
	/** 学分         */ private Double    stuCredit;
	/** 学生班级Id   */ private Integer   stuClaId ; //-1代表未分配班级
	/** 学生年级     */ private Integer   stuGrade ; //就是入学年份，系统代码35
	/** 备注         */ private String    stuRemark;
	/** 2正常(默认)  */ private Byte      stuTo    ; //3休学 4退学 5毕业
	/** 学生记录状态 */ private Byte      stuState ; //2正常(默认) 3历史 4关闭

	/** 排序字段   */ private String[] orderFd; //排序字段的名称，倒序前面加“@”，可以多个
	/** 年级Id(组) */ private String   grades ; //多个Id用“,”隔开
	/** 班级Id(组) */ private String   claIds ; //多个Id用“,”隔开
	/** 学生Id(组) */ private String   stuIds ; //多个Id用“,”隔开
	/** 保存类型   */ private Byte     svType ;

	//==================================================================================================================
	// 查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询
	 * 使用者：
	 *
	 * URL：p/xkTask!query.action
	 *
	 * 参数
	 * start        可选   记录开始位置
	 * limit        可选   获取记录数量
	 * stuId        可选   学生Id
	 * xkpId        可选   选课计划Id
	 * taskState    可选   任务状态 1未开启 2开启(默认) 3关闭 4完成
	 * orderFd      可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[StuXkTask]}
	 *
	 */

	//==================================================================================================================
	// 查询2 -- 引入：选课计划 + 学生信息
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询2 -- 引入：选课计划 + 学生信息
	 * 使用者：
	 *
	 * URL：p/xkTask!query2.action
	 *
	 * 参数
	 * start        可选   记录开始位置
	 * limit        可选   获取记录数量
	 * stuId        可选   学生Id
	 * xkpId        可选   选课计划Id
	 * taskState    可选   任务状态 1未开启 2开启(默认) 3关闭 4完成
	 * termId       可选   学期Id
	 * planNO       可选   计划编号
	 * planName     可选   计划名称
	 * xkpState     可选   选课计划状态 1未开启 2开启(默认) 3关闭 4完成
	 * stuNO        可选   学籍号
	 * stuName      可选   学生姓名
	 * stuSex       可选   学生性别 男(默认)、女
	 * stuCredit    可选   学分
	 * stuClaId     可选   学生班级Id -1代表未分配班级
	 * stuGrade     可选   学生年级 就是入学年份，系统代码35
	 * stuRemark    可选   备注
	 * stuTo        可选   2正常(默认) 3休学 4退学 5毕业
	 * stuState     可选   学生记录状态 2正常(默认) 3历史 4关闭
	 * orderFd      可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuXkTask]}
	 *
	 */

	//==================================================================================================================
	// 新增
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：新增
	 * 使用者：
	 *
	 * URL：p/xkTask!newRec.action
	 *
	 * 参数
	 * xkpId      必需   选课计划Id
	 * stuIds     3选1   学生Id 多个Id用“,”隔开  3选1优先级别：stuIds > claIds > grades
	 * claIds     3选1   班级Id 多个Id用“,”隔开
	 * grades     3选1   年级Id 多个Id用“,”隔开
	 * startTime  可选   任务开启时间 默认取计划时间
	 * endTime    可选   任务关闭时间 默认取计划时间
	 * overTime   可选   任务完成时间 默认取计划时间
	 * taskState  可选   任务状态 1未开启 2开启(默认) 3关闭 4完成
	 * svType     可选   2＝如果已存在，则进行更新，默认＝如果已存在，则终止
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"data":{StuXkTask}}
	 *
	 */

	//==================================================================================================================
	// 修改
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：修改
	 * 使用者：
	 *
	 * URL：p/xkTask!edit.action
	 *
	 * 参数
	 * xkpId      必需   选课计划Id
	 * stuIds     3选1   学生Id 多个Id用“,”隔开  3选1优先级别：stuIds > claIds > grades
	 * claIds     3选1   班级Id 多个Id用“,”隔开
	 * grades     3选1   年级Id 多个Id用“,”隔开
	 * startTime  可选   任务开启时间
	 * endTime    可选   任务关闭时间
	 * overTime   可选   任务完成时间
	 * taskState  可选   任务状态 1未开启(默认) 2开启 3关闭 4完成
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

	//==================================================================================================================
	// 删除
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：删除  注意：不能删除“4完成”状态的数据
	 * 使用者：
	 *
	 * URL：p/xkTask!del.action
	 *
	 * 参数
	 * xkpId      必需   选课计划Id
	 * stuIds     3选1   学生Id 多个Id用“,”隔开  3选1优先级别：stuIds > claIds > grades
	 * claIds     3选1   班级Id 多个Id用“,”隔开
	 * grades     3选1   年级Id 多个Id用“,”隔开
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

	//==================================================================================================================
