<template>
	<view class="rank page-bg min-height-100">
		<a-navbar :title="navbarTitle" @back="back"></a-navbar>
		<view class="content">
			<!-- <u-tabs-swiper ref="tabs" :list="list" :is-scroll="false" :current="current" @change="change" v-if="list.length"></u-tabs-swiper> -->
			<u-gap height="20"></u-gap>
			<view class="line bg-white border-bottom height-line-80 padding-lr-40 flex a-center j-start flex-row">
				<view class="margin-r-20" @click="isShowPicker=true">
					<text class="inline-block">筛选</text>
					<u-icon :name="isShowPicker?'arrow-up-fill':'arrow-down-fill'" class="margin-l-10" color="#c8c9cc" ></u-icon>
				</view>
				<text>{{currentTime}}</text>
			</view>
			<view class="flex a-center height-line-80 j-between padding-lr-40 flex-row bg-white border-bottom" v-if="rankDetail">
				<view class="flex1">你的业绩为：{{rankDetail.zyeji}}</view>
				<view class="flex1">排名为：{{rankDetail.paiming}}</view>
			</view>
			<view class="box flex a-center j-start flex-row bg-white padding-lr-40" v-for="(item,index) in cardList" :key='index' v-if="rankDetail">
				<view class="left flex-shrink">
					<u-avatar :src='item.touxiang'></u-avatar>
				</view>
				<view class="mid flex1">
					<view class="name">{{item.ygmingcheng}}（{{item.bm}}）</view>
					<view class="text-blue">￥{{item.sum || 0}}</view>
				</view>
				<view class="right flex-shrink">
					{{item.paiming}}
				</view>
			</view>
		</view>
		<u-picker v-model="isShowPicker" mode="time" :params='params' @confirm='confirm'></u-picker>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value1: 1,
				isShowPicker:false,
				current: 0,
				currentTime:"",
				userInfo:null,
				pageType:"",
				navbarTitle:'',
				rankDetail:null,
				params:{
					year: true,
					month: true,
					day: false,
					hour: false,
					minute: false,
					second: false,
					timestamp: false
				},
				cardList:[],
				cardPersonList:[],
				
				options1: [{
						label: '默认排序',
						value: 1,
					},
					{
						label: '距离优先',
						value: 2,
					},
					{
						label: '价格优先',
						value: 3,
					}
				],
				list:[],
				listShopGroup: [{
					name: '门店排名'
				}, {
					name: '店铺总榜'
				}],
				listPerson: [{
					name: '店铺排名'
				}, {
					name: '个人总榜'
				}],
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.userInfo=this.$tool.uniGetStorage('userInfo')
			this.currentTime=this.$u.timeFormat(String(new Date().getTime()), 'yyyy-mm')
			this.init()
		},
		methods:{
			init(){
				switch(this.pageType){
					case "person":
						this.list=this.listPerson
						this.navbarTitle='个人业绩排名'
						this.getPerson()
						break
					case "shopGroup":
						this.list=this.listShopGroup						
						this.navbarTitle='店组业绩排名'
						this.getS(4)
						break
					case "shop":
						this.list=[]
						this.getS(3)
						this.navbarTitle='门店业绩排名'
						break
				}
			},
			getS(type){
				this.$tool.uniRequest({
					url:"juece/cxpmzdyeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						datetimes:this.currentTime,
						bumen_t:type,
						bumen_id:this.userInfo.bumen_id
					},
					success:(res)=>{
						this.cardList=res.list || []
						this.rankDetail=res
					}
				})
			},
			getPerson(){
				this.$tool.uniRequest({
					url:"juece/cxpmgryeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						datetimes:this.currentTime
					},
					success:(res)=>{
						this.cardList=res.list || []
						this.rankDetail=res
					}
				})
			},
			back(){
				this.$tool.uniRedirectTo({
					url:`/pages/home/base/index?type=${this.pageType}`,
				})
			},
			change(index){
				this.current = index
			},
			confirm(val){
				this.currentTime=val.year+'-'+val.month
				this.init()
			}
		}
	}
</script>

<style scoped lang="scss">
	.rank{
		.content{
			.box{
				padding:20rpx 40rpx;
				.left{
					margin-right: 20rpx;				
				}
				.mid{
					.name{
						margin-bottom: 10rpx;
					}
				}
				.right{
					color: #dd6161;
				}
			}
		}
	}
</style>
