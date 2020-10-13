<template>
	<view class="income-gu page-bg min-height-100">
		<a-navbar :title="navbarTitle" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content" v-if="userInfo">
			<view class="header-person header-bg text-white flex a-center j-start flex-row" v-if="pageType==='person'">
				<view class="left flex-shrink">
					<u-avatar :src='userInfo.touxiang' size="120"></u-avatar>
				</view>
				<view class="right flex1">
					<view class="name">
						姓名：{{userInfo.ygmingcheng}}
					</view>
					<view class="time">
						当前职务：{{userInfo.gangwei}}
					</view>
					<view class="time">
						当前店铺：{{userInfo.bumen}}
					</view>
				</view>
			</view>
			<view class="header-shop-group header-bg text-white flex a-center j-start flex-row" v-if="pageType==='shopGroup' || pageType==='shop'">
				<view class="right flex1">	
					<view class="time" v-if="currentMonthPerformance">
						{{pageType == 'shopGroup'?"所属组":'所属店'}}：{{currentMonthPerformance.bumen}}
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('sameMonth')">
					<text>当月业绩（{{currentMonth}}）</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap">
					<view class="box-1 ">
						<view class="name">
							分成业绩
						</view>
						<view class="value text-blue">
							{{currentMonthPerformance.yeji}}
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							已收业绩
						</view>
						<view class="value text-blue">
							{{currentMonthPerformance.shishou}}
						</view>
					</view>
				</view>
			</view>
			<view class="table bg-white" v-if="allPerformance">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('total')">
					<text>总业绩</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap flex-wrap">
					<view class="box-1">
						<view class="name">
							分成业绩
						</view>
						<view class="value text-blue">
							{{allPerformance.yeji}}
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							已收业绩
						</view>
						<view class="value text-blue">
							{{allPerformance.shishou}}
						</view>
					</view>
				</view>
			</view>
			<view class="table bg-white" v-if="rankPerformance">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('rank')">
					<text>业绩排名</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap flex-wrap" v-if="rankList.length">
					<view class="box-1 flex a-center j-center flex-column" v-for="(item,index) in rankList" :key='index'>
						<view class="value text-blue">
							{{item.value}}
						</view>
						<view class="name">
							{{item.name}}
						</view>
					</view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				pageType:"person",
				userInfo:null,
				rankList:[],
				navbarTitle:'',
				currentMonthPerformance:'',
				currentMonth:'',
				allPerformance:null,
				rankPerformance:null,
				rankShopList:[
					{
						name:"当月组排名",
						prop:'ypaiming',
						value:"0"
					},{
						name:"组总排名",
						prop:'zpaiming',
						value:"0"
					},
				],
				rankShopGroupList:[
					{
						name:"当月店排名",
						prop:'ypaiming',
						value:"0"
					},{
						name:"店总排名",
						prop:'zpaiming',
						value:"0"
					},
				],
				rankPersonList:[
					{
						name:"当月个人排名",
						prop:'yrpaiming',
						value:"0"
					},{
						name:"个人总业绩排名",
						prop:'zrpaiming',
						value:"0"
					}
				]
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.userInfo=this.$tool.uniGetStorage('userInfo')
			this.currentMonth=this.$u.timeFormat(String(new Date().getTime()), 'yyyy-mm')
			this.userInfo && this.init()
		},
		methods:{
			init(){
				switch(this.pageType){
					case "person":
						this.rankList=this.rankPersonList					
						this.getPerson()
						this.navbarTitle="个人业绩"
						break
					case "shopGroup":
						this.rankList=this.rankShopList
						this.getS(4)
						this.navbarTitle="组业绩"
						break
					case "shop":
						this.rankList=this.rankShopList
						this.getS(3)
						this.navbarTitle="店业绩"
						break
				}
				
			},
			getS(type){
				this.getSCurrentMonth(type)
				this.getSAll(type)
				this.getSRank(type)
			},
			getSCurrentMonth(type){
				this.$tool.uniRequest({
					url:"juece/zdyeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						bumen_t:type,  //3店4组
						bumen_id:this.userInfo.bumen_id,
						datetimes:this.currentMonth
					},
					success:(res)=>{
						this.currentMonthPerformance=res
					}
				})	
			},
			getSAll(type){
				this.$tool.uniRequest({
					url:"juece/tjzdyeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						bumen_t:type,  //3店4组
						bumen_id:this.userInfo.bumen_id,
					},
					success:(res)=>{
						this.allPerformance=res
					}
				})	
			},
			getSRank(type){
				this.$tool.uniRequest({
					url:"juece/pmzdyeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						bumen_t:type,  //3店4组
						bumen_id:this.userInfo.bumen_id,
					},
					success:(res)=>{
						this.rankPerformance=res
						this.rankPersonList.forEach(item=>{
							for(let i in res){
								if(item.prop===i){
									item.value=res[i] || '0'
								}
							}
						})
					}
				})	
			},
			getPerson(){
				this.getPersonCurrentMonth()
				this.getPersonAll()
				this.getPersonRank()
			},
			getPersonRank(){
				this.$tool.uniRequest({
					url:"juece/pmgryeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId')
					},
					success:(res)=>{
						this.rankPerformance=res
						this.rankPersonList.forEach(item=>{
							for(let i in res){
								if(item.prop===i){
									item.value=res[i] || '0'
								}
							}
						})
					}
				})	
			},
			getPersonAll(){
				this.$tool.uniRequest({
					url:"juece/tjgryeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId')
					},
					success:(res)=>{
						this.allPerformance=res
					}
				})	
			},
			getPersonCurrentMonth(){
				this.$tool.uniRequest({
					url:"juece/gryeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						datetimes:this.currentMonth
					},
					success:(res)=>{
						this.currentMonthPerformance=res
					}
				})	
			},
			toOther(type){
				let url=''
				switch(type){
					case 'sameMonth':
						url=`/pages/home/base/sameMonth?type=${this.pageType}`
						break
					case 'total':
						url=`/pages/home/base/total?type=${this.pageType}`
						break
					case 'rank':
						url=`/pages/home/base/rank?type=${this.pageType}`
						break
					default :
						break
				}
				this.$tool.uniNavigateTo({url})
			}
		}
	}
</script>

<style scoped lang="scss">
	.header-bg{
		background-color: #19be6b;
	}
	.income-gu {
		.content {
			.header-person {
				padding: 0 80rpx;
				height: 240rpx;

				.left {
					width: 140rpx;

				}

				.right {
					.name {
						margin-bottom: 20rpx;
					}
				}
			}
			.header-shop-group {
				padding: 0 40rpx;
				height: 120rpx;
			
				.left {
					width: 140rpx;
			
				}
			
				.right {
					.name {
						margin-bottom: 20rpx;
					}
				}
			}
			.table {
				margin: 40rpx 0;
				padding: 0 40rpx;

				.line {
					height: 80rpx;
					line-height: 80rpx;
				}

				.box-1-wrap {
					/* color: #acacac; */
					min-height: 120rpx;
					padding: 20rpx 0;

					.box-1 {
						width: 50%;
						margin-bottom: 20rpx;

						.name {
							margin-top: 10rpx;
						}
						
					}
				}
			}
		}
	}
</style>
