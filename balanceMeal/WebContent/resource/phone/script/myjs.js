var menus,headerH,nav_icon;
apiready = function(){

	fnInitEventListenner();

	var header = $api.byId('header');
	var balance_meal_top = $api.byId('balance_meal_top');
	var nav = $api.byId('nav');
	$api.fixStatusBar(header);
	headerH = $api.offset(header).h;
	var navH = $api.offset(nav).h;
	menus = $api.domAll(nav,'.nav_menu');
	nav_icon = $api.domAll(nav,'.nav_icon');
	var frames = [];
	var url1 = ['./main_frame.html','./balance_meal.html','./nutrition.html','./login.html'];

	for(var i=0; i<menus.length; i++){
		frames.push({
			name:'main_frame_'+i,
			url:url1[i]
		});
	}

	api.openFrameGroup(
		{
			name:'mainFrameGroup',
			scrollEnabled:true,
			rect:{
				x:0,
				y:headerH,
				w:'auto',
				h:api.winHeight - headerH - navH
			},
			index:0,
			frames:frames
		},function(ret,err){ 
			fnSetNavMenuSelected(ret.index); 
		}
	);


};



/*--------------- 菜单导航 -------------------*/
function fnSetNavMenuIndex(index_){
	fnSetNavMenuSelected(index_);
	api.setFrameGroupIndex({
		name:'mainFrameGroup',
		index:index_,
		scroll:true
	});

}

/* ------- 控制导航栏的样式 ------- */
function fnSetNavMenuSelected(index_){
	for(var i=0; i<menus.length; i++){
		if(index_ == i){
			$api.addCls(menus[i],'nav_active');
			$api.addCls(nav_icon[i],'nav_icon_active');
			
		}else{
			$api.removeCls(menus[i],'nav_active');
			$api.removeCls(nav_icon[i],'nav_icon_active');
		}
	}
}

/*--------------- 点击进入个人中心（如果还没登录则进入登录页面） -------------------*/
function fnOpenMessageWin(){
	api.openWin({
        name: 'message_top',
        url: './message_top.html',
        pageParam: {
            name: 'test'
        }
    });
	
}

/*--------- 选择所在城市 ---------- */
function fnOpenCitySelectorFrame(){
    api.openFrame({
        name: 'cityselector_frame',
        url: './cityselector_frame.html',
        bgColor: 'rgba(0, 0, 0, 0.8)',
        rect: {
            x: 0,
            y: headerH,
            w: 'auto',
            h: 'auto'
        },
        pageParam: {
            name: 'test'
        },
        bounces: true
    });
}

/*--------- 退出应用的提示框 --------*/
function fnInitEventListenner(){
	api.addEventListener({
		name:'keyback'
	},function(ret,err){
		api.confirm({
		    title: '提示',
		    msg: '是否退出应用',
		    buttons: ['确定', '取消']
		}, function(ret, err){
		    if( ret.buttonIndex == 1 ){
		        api.closeWidget();
		    }
		});
	});
}

