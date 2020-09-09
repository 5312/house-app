<template>
	<view class="home">
		<u-navbar :background="headerBg" title="" :is-back="false" :height='50' :border-bottom='false'>
			<view class="header flex j-between a-center flex-row">
				<view class="left" @click.stop.prevet="isShowPopup=true">
					<u-icon name="list"></u-icon>
				</view>
				<view class="right flex j-end a-center flex-row">
					<u-icon name="scan" class="scan-icon"></u-icon>
					<u-icon name="chat"></u-icon>
				</view>
			</view>
		</u-navbar>
		<view class="banners">
			<u-swiper :list="swiperList" :mode="'dot'" :height="400"></u-swiper>
		</view>
		<view class="content">
			<a-grid-list :list='personList' title="个人中心"></a-grid-list>
			<u-gap height="20" bg-color="#f4f4f5"></u-gap>
			<a-grid-list :list='achievementList' title="业绩板块"></a-grid-list>
			<u-gap height="20" bg-color="#f4f4f5"></u-gap>
			<a-grid-list :list='otherList' title="其他板块"></a-grid-list>
		</view>
		<u-popup v-model="isShowPopup" length="66%" class="home-left-popup">
			<view class="relative h100 wrap">
				<view class="popup-header">
					<view class="line1 flex a-center j-center flex-row">
						<u-avatar src="http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg" 
							size="120" class="flex-shrink"></u-avatar>
						<view class="line-right flex1">
							<view class="name">刘明</view>
							<view class="Job">实习置业顾问</view>
						</view>
					</view>
					<view class="line margin-b-20">审核区域大股东-杨李敏</view>
					<view class="line">橡树2号店-M组-李明012345</view>
				</view>
				<view class="popup-contet">
					<view class="pop-line border-bottom flex a-center j-start flex-row" v-for="(item,index) in popList" :key="index" @click="popHandle(item)">
						<u-icon name="man-add" class="icon"></u-icon>
						<view class="label">
							{{item.name}}
							<text v-if="item.text" :style="{color:item.textColor || 'black'}">{{item.text}}</text>
						</view>
					</view>
				</view>
				<view class="popup-footer absolute border-top" @click="isShowPopup=false">
					<u-icon name="close-circle" ></u-icon>
					<text>退出</text>
				</view>
			</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				headerBg:{
					backgroundColor: '#EDEDED',
				},
				isShowPopup:false,
				swiperList: [{
						image: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					}
				],
				popList:[
					{
						icon:"",
						name:"修改头像",
						prop:"changeAvatar"
					},{
						icon:"",
						name:"修改密码",
						prop:"changePassword"
					},{
						icon:"",
						name:"退出账号",
						prop:"loginOut"
					}
				],
				personList:[
					{
						name:"考勤打卡",
						img:"../../static/icon/a.png",
						path:"/pages/home/clockIn/index"
					},{
						name:"调店申请",
						img:"../../static/icon/b.png",
						path:"/pages/home/shopTransferApplication/index"
					},{
						name:"离职申请",
						img:"../../static/icon/c.png",
						path:"/pages/home/quitApplication/index"		
					},{
						name:"个人资料",
						img:"../../static/icon/d.png",
						path:"/pages/home/personalInfo/index"	
					},{
						name:"门店导航",
						img:"../../static/icon/e.png",
						path:"/pages/home/shopMap/index"
					},{
						name:"员工位置",
						img:"../../static/icon/f.png",
						path:"/pages/home/staffLocation/index"	
					},{
						name:"工资管理",
						img:"../../static/icon/g.png",
						path:"/pages/home/incomeGu/index"
					},{
						name:"成长树",
						img:"../../static/icon/h.png",
						path:"/pages/home/growthTree/index"
					},{
						name:"报单管理",
						img:"../../static/icon/m.png",
						path:"/pages/home/reimbursement/index"
					},{
						name:"开店申请",
						img:"../../static/icon/y.png",
						path:"/pages/home/reimbursement/index"
					},{
						name:"闭店申请",
						img:"../../static/icon/z.png",
						path:"/pages/home/reimbursement/index"
					},
				],
				achievementList:[
					{
						name:"个人业绩",
						img:"../../static/icon/i.png",
						path:"/pages/home/base/index?type=person"
					},{
						name:"店组业绩",
						img:"../../static/icon/j.png",
						path:"/pages/home/base/index?type=shopGroup"
					},{
						name:"门店业绩",
						img:"../../static/icon/k.png",
						path:"/pages/home/base/index?type=shop"
					},{
						name:"未收账",
						img:"../../static/icon/l.png",
						path:"/pages/home/uncollected/index"
					}
				],
				otherList:[
					{
						name:"规章制度",
						img:"../../static/icon/n.png",
						path:"/pages/home/rules/index"
					}
				]
			}
		},
		onLoad(){
			// this.$tool.uniShowToast({title:"请输入用户名",icon:"none"})
		},
		methods:{
			popHandle(item){
				switch(item.prop){
					case 'loginOut':
						this.loginOut()
						break
					default :
						break
				}
			},
			loginOut(){
				this.$tool.uniRequest({
					url:"login/logout",
					method:'GET',
					// isLogin:true,
					success:(res)=>{
						this.$tool.uniRemoveStorage('token')
						this.$tool.uniReLaunch({
							url:"/pages/login/index"
						})
						console.log(res)
					}
				})
			}
		}
	}
</script>
<style scoped lang="scss">
	.home {
		.header {
			background: #EDEDED;
			height: 90rpx;
			font-size: 40rpx;
			padding: 0 40rpx;		
			width: 100%;
			outline: none;
			border: none;
			.scan-icon {
				margin-right: 20rpx;
			}
		}
		&-left-popup{
			.wrap{	
				.popup-header{
					padding: 100rpx 20rpx 40rpx 20rpx;
					background: #f4f4f5;
					.line1{
						.line-right{
							margin-left: 20rpx;
						}
						margin-bottom: 20rpx;
					}
					.line{
						text-align: center;
					}
				}
				.popup-contet{
					margin: 0 20rpx;				
					height: 100rpx;
					font-size: 30rpx;
					line-height: 100rpx;
					color: black;
					.pop-line{
						padding:0 30rpx;
						.icon{
							color: #B1B1B1;
							font-size: 30rpx;
						}
						.label{
							margin-left: 24rpx;
							>text{
								margin-left: 10rpx;
								display: inline-block;
							}
						}
					}
				}
				.popup-footer{
					left: 0;
					right: 0;
					bottom: 0;
					height: 100rpx;
					text-align: center;
					line-height: 100rpx;
					>text{
						margin-left: 6rpx;
						display: inline-block;
					}
				}
			}
		}
	}
</style>
