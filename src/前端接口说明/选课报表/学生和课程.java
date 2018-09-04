/** 学生和课程 */

	/** 待选课Id     */ private Integer   selCouId  ;
	/** 选课计划Id   */ private Integer   xkpId     ;
	/** 课程类型     */ private Integer   couType   ; //系统代码11 2普通(默认) 4自修研习
	/** 父课程Id     */ private Integer   parCouId  ; //组合课程时有
	/** 课程编号     */ private String    couNO     ;
	/** 课程名称     */ private String    couName   ;
	/** 最低学分     */ private Double    creditMin ;
	/** 人数下限     */ private Short     manMin    ; //默认-1代表无限制
	/** 人数上限     */ private Short     manMax    ; //默认-1代表无限制
	/** 男生下限     */ private Short     boyMin    ; //默认-1代表无限制
	/** 男生上限     */ private Short     boyMax    ; //默认-1代表无限制
	/** 女生下限     */ private Short     girlMin   ; //默认-1代表无限制
	/** 女生上限     */ private Short     girlMax   ; //默认-1代表无限制
	/** 当前男生数   */ private Short     boyNum    ;
	/** 当前女生数   */ private Short     girlNum   ;
	/** 学生操作状态 */ private Byte      stuOpState; //1不可见、2可选课(默认)
	/** 委员操作状态 */ private Byte      memOpState; //1不可见(默认)、2可见、3可评价
	/** 教师操作状态 */ private Byte      thrOpState; //控制教师对学生的评价，2不可评(默认)、3可评价
	/** 开课状态     */ private Byte      couState  ; //2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	/** 审核状态     */ private Byte      audit     ; //1未审核(默认) 2已审核
	/** 子课程数量   */ private Long      chiNum    ;
	/** 学生Id       */ private Integer   stuId     ;
	/** 任务开启时间 */ private Timestamp startTime ;
	/** 任务关闭时间 */ private Timestamp endTime   ;
	/** 任务状态     */ private Byte      taskState ; //1未开启 2开启(默认) 3关闭 4完成
	/** 学籍号       */ private String    stuNO     ;
	/** 学生姓名     */ private String    stuName   ;
	/** 学生性别     */ private String    stuSex    ; //男、女
	/** 学生学分     */ private Double    stuCredit ;
	/** 学生班级Id   */ private Integer   stuClaId  ; //-1代表未分配班级
	/** 学生年级     */ private Integer   stuGrade  ;
	/** 可选班级Id   */ private String    claIds    ;
	/** 可选班级学分 */ private String    claCreMins;
	/** 可选班级名称 */ private String    claNms    ;
	/** 可选年级Id   */ private String    grades    ;
	/** 可选年级学分 */ private String    graCreMins;
	/** 可选年级名称 */ private String    gradeNms  ;

	/** 排序字段 */ private String[] orderFd; //排序字段的名称，倒序前面加“@”，可以多个

	/** 0=不可选、1=可选                      */ public byte canSel   = 1; //不考虑：选课时间、选课任务状态、人数上限
	/** 时间状态：0=未到、1=正常、2=已过      */ public byte stTime   = 1;
	/** 性别状态：0=不符、1=正常              */ public byte stSex    = 1;
	/** 学分状态：0=不符、1=正常              */ public byte stCredit = 1;
	/** 年级状态：0=不符、1=正常、2=学分不足  */ public byte stGrade  = 1;
	/** 班级状态：0=不符、1=正常、2=学分不足  */ public byte stClass  = 1;
	
	//==================================================================================================================
	// 查询学生与待选课程的关系
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询学生与待选课程的关系。用于查看：课程可被那些学生选 或 学生可以选什么课程 
	 * 使用者：
	 *
	 * URL：p/stuAndCou!query.action
	 *
	 * 参数
	 * start        可选   记录开始位置
	 * limit        可选   获取记录数量
	 * selCouId     可选   待选课Id
	 * xkpId        可选   选课计划Id
	 * couType      可选   课程类型 系统代码11 2普通(默认) 4自修研习
	 * parCouId     可选   父课程Id 组合课程时有
	 * couNO        可选   课程编号
	 * couName      可选   课程名称
	 * stuOpState   可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState   可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * thrOpState   可选   教师操作状态 控制教师对学生的评价，2不可评(默认)、3可评价
	 * couState     可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit        可选   审核状态 1未审核(默认) 2已审核
	 * stuId        可选   学生Id
	 * taskState    可选   任务状态 1未开启 2开启(默认) 3关闭 4完成
	 * stuNO        可选   学籍号
	 * stuName      可选   学生姓名
	 * stuSex       可选   学生性别 男、女
	 * stuClaId     可选   学生班级Id -1代表未分配班级
	 * stuGrade     可选   学生年级 就是入学年份，系统代码35,默认-1代表未指定年级
	 * orderFd      可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[R_StuAndCou]}
	 *
	 */

	//==================================================================================================================
