<template>
	<view class="home">
		<u-navbar :background="headerBg" title="" :is-back="false" :height='50' :border-bottom='false'>
			<view class="header flex j-between a-center flex-row">
				<view class="left" 
					  @click.stop="isShowPopup=true">
					<u-icon name="list"></u-icon>
				</view>
				<view class="right flex j-end a-center flex-row">
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
				<view class="popup-header" v-if="userInfo">
					<view class="line1 flex a-center j-center flex-row">
						<u-avatar :src="avatar" 
							size="120" class="flex-shrink"></u-avatar>
						<view class="line-right flex1">
							<view class="name">{{userInfo.ygmingcheng}}</view>
							<view class="Job">{{userInfo.gangwei}}</view>
						</view>
					</view>
					<view class="line">{{userInfo.bumen}}-{{userInfo.ygmingcheng}}{{userInfo.ygbianhao}}</view>
				</view>
				<view class="popup-contet">
					<view class="pop-line border-bottom flex a-center j-start flex-row" v-for="(item,index) in popList" :key="index" @click="popHandle(item)">
						<u-icon :name="item.icon ||'man-add'" class="icon"></u-icon>
						<view class="label">
							{{item.name}}
							<text v-if="item.text" :style="{color:item.textColor || 'black'}">{{item.text}}</text>
						</view>
					</view>
				</view>
				<view class="popup-footer absolute border-top" @click="loginOut">
					<u-icon name="close-circle" ></u-icon>
					<text>退出账号</text>
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
				userInfo:null,
				swiperList: [],
				popList:[
					{
						icon:"",
						name:"修改头像",
						prop:"changeAvatar"
					},{
						icon:"lock",
						name:"修改密码",
						prop:"changePassword"
					},
					 {
						icon:"edit-pen",
						name:"服务协议",
						prop:"fuwuxieyi"
					} ,
					 {
						icon:"list",
						name:"隐私条款",
						prop:"yinsitiaokuan"
					} 
				],
				personList:[
					{
						name:"考勤打卡",
						img:require("../../static/icon/a.png"),
						path:"/pages/home/clockIn/index"
					},{
						name:"调店申请",
						img:require("../../static/icon/b.png"),
						path:"/pages/home/shopTransferApplication/index"
					},{
						name:"离职申请",
						img:require("../../static/icon/c.png"),
						path:"/pages/home/quitApplication/index"		
					},{
						name:"个人资料",
						img:require("../../static/icon/d.png"),
						path:"/pages/home/personalInfo/index"	
					},{
						name:"门店导航",
						img:require("../../static/icon/e.png"),
						path:"/pages/home/shopMap/index"
					},{
						name:"员工位置",
						img:require("../../static/icon/f.png"),
						path:"/pages/home/staffLocation/index"	
					},{
						name:"工资管理",
						img:require("../../static/icon/g.png"),
						path:"/pages/home/incomeGu/index"
					},{
						name:"成长树",
						img:require("../../static/icon/h.png"),
						path:"/pages/home/growthTree/index"
					},{
						name:"报单管理",
						img:require("../../static/icon/m.png"),
						path:"/pages/home/reimbursement/index"
					},{
						name:'审批管理',
						img:require("../../static/icon/shenpi.png"),
						path:'/pages/approve/shenpi'
					},{
						name:"开店申请",
						img:require("../../static/icon/y.png"),
						path:"/pages/home/shopApply/shopPage"
					},{
						name:"闭店申请", 
						img:require("../../static/icon/z.png"),
						path:"/pages/home/closeDian/closeDian"
					},
				],
				achievementList:[
					{
						name:"个人业绩",
						img:require("../../static/icon/i.png"),
						path:"/pages/home/base/index?type=person"
					},{
						name:"组业绩",
						img:require("../../static/icon/j.png"),
						path:"/pages/home/base/index?type=shopGroup"
					},{
						name:"店业绩",
						img:require("../../static/icon/k.png"),
						path:"/pages/home/base/index?type=shop"
					},{
						name:"未收账",
						img:require("../../static/icon/l.png"),
						path:"/pages/home/uncollected/index"
					}
				],
				otherList:[
					{
						name:"规章制度",
						img:require("../../static/icon/n.png"),
						path:"/pages/home/rules/index"
					}
				],
				uploadPath:'http://ming.ydeshui.com/api/ad/homead '
			}
		},
		created() {
			// 监听从裁剪页发布的事件，获得裁剪结果
			uni.$on('uAvatarCropper', path => {
				this.$tool.uniShowLoading({})
				// 可以在此上传到服务端
				uni.uploadFile({
					url: this.uploadPath,
					filePath: path,
					name: 'file',
					complete: res => {
						this.avatar = path;
						this.$tool.uniHideLoading()
					}
				});
			});
		},
		onLoad(){
			this.userInfo=this.$tool.uniGetStorage('userInfo');
			this.avatar = this.userInfo.touxiang;
			const token = this.$tool.uniGetStorage('token')		
			if(!token){
				this.$tool.uniReLaunch({
					url: "/pages/login/index"
				})
			}
			this.init()
		},
		methods:{
			init(){
				this.getBanners()
			},
			getBanners(){
				this.$tool.uniRequest({
					url:"ad/homead",
					method:'GET',
					success:(res)=>{
						this.swiperList=res
						this.swiperList.forEach(item=>{
							item.image=item.img
						})
					}
				})
			},
			chooseAvatar() {
				this.$u.route({
					url: '/uview-ui/components/u-avatar-cropper/u-avatar-cropper',
					params: {
						// 输出图片宽度，高等于宽，单位px
						destWidth: 300,
						// 裁剪框宽度，高等于宽，单位px
						rectWidth: 200,
						// 输出的图片类型，如果'png'类型发现裁剪的图片太大，改成"jpg"即可
						fileType: 'jpg'
					}
				});
			},
			popHandle(item){
				const pThis = this;
				const navigator = {
					changeAvatar:function(){
						pThis.chooseAvatar();
						// let avatar = pThis.userInfo.touxiang;
						// pThis.$tool.uniRedirectTo({
						// 	url:`/pages/home/changeAvatar?avatar=${avatar}`,
							
						// })
					}, 
					loginOut:function(){
						pThis.loginOut()
					},
					changePassword:function(){
						pThis.$tool.uniRedirectTo({
							url:'/pages/home/changePassword'
						})
					},
					fuwuxieyi:function(){
						pThis.$tool.uniRedirectTo({
							url:'/pages/home/slider/slider'
						})
					},
					yinsitiaokuan:function(){
						pThis.$tool.uniRedirectTo({
							url:'/pages/home/slider/yinsi'
						})
					}
				};
				//调用
				navigator[item.prop]()
			},
			loginOut(){
				this.$tool.uniRequest({
					url:"login/logout",
					method:'GET',
					success:(res)=>{
						this.$tool.uniRemoveStorage('token')
						this.$tool.uniReLaunch({
							url:"/pages/login/index"
						})
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
