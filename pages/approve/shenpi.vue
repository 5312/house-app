<template>
	<view>
		<a-navbar title="审批" @back="$tool.uniSwitchTab({url:'/pages/home/index'})" background-color='#fff'></a-navbar>
		<u-tabs  active-color="#00ff7f" inactive-color="#000" :current='current' @change="change" bar-width="150"  :list="list" :is-scroll="false"></u-tabs>
		<view v-if='current == 0' class="wrap">
			<view v-for="(x,y) in detailList" :key='y'>
				<view class="detail" @click="xiangqing(x.id)">
					<view class="title">{{ x.to_title }}</view>
					<view class="time">{{ x.addtime }}</view>
					<view class="time" v-html="x.neirong"></view>
					<view class="bottom">
						<view class="left"><u-icon name='home-fill' class="ic" ></u-icon>{{x.bumen}}</view>
						<u-tag v-if="x.sh_zhuangtai == 0" text="店长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 1" text="区长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 2" text="后台审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 3" text="结案" type="info" shape="circle"></u-tag>
					</view>
				</view>
				<view class="hi"></view>
			</view>
		</view>
		<view v-if="current == 1" class="wrap">
			<view v-for="(x,y) in detailList" :key='y'>
				<view class="detail" @click="xiangqing(x.id)">
					<view class="title">{{ x.to_title }}</view>
					<view class="time">{{ x.addtime }}</view>
					<view class="time" v-html="x.neirong"></view>
					<view class="bottom">
						<view class="left"><u-icon name='home-fill' class="ic" ></u-icon>{{x.bumen}}</view>
						<u-tag v-if="x.sh_zhuangtai == 0" text="店长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 1" text="区长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 2" text="后台审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 3" text="结案" type="info" shape="circle"></u-tag>
					</view>
				</view>
				<view class="hi"></view>
			</view>
		</view>
	</view>
</template>

<script>
	import api from '@/utils/api/info.js'
	export default {
		data() {
			return {
				current:0,
				detailList:[],
				totalcount:'',
				totalpage:'',
				intPage:1,
				list: [{
					name: '我的审批'
				}, {
					name: '我提交的审批'
				}],
			}
		},
		watch:{
			current(){
				//切换就清空列表
				this.detailList = [];
			}
		},
		onLoad(){
			this.request()//审批列表
		},
		onReachBottom() {
			//触底一次页码加一
			if(this.intPage < this.totalpage){
				this.intPage++;
				this.request();
			}
		},
		methods: {
			change(index){
				this.current  = index;
				this.request()
			},
			request(){
				let _this = this;
				let data = {
					type:this.current,//0我的审批1我提交的审批
					p:_this.intPage//页码
				};
				api.ywsp(data).then(res => {
					_this.detailList.push(...res.list);
					_this.totalcount = res.totalcount//总共条数
					_this.totalpage = res.totalpage//总共页数
					_this.intPage = res.intPage//当前页码
				});
			},
			xiangqing(id){
				//跳转详情
				this.$tool.uniNavigateTo({
					url:`/pages/approve/shenpiDdetail?id=${id}&current=${this.current}`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>

	page{
		background-color:  #EDEDED;

	}
	.hi{
		height: 20rpx;
		background: #EDEDED;
		width:200%;
	}
	.wrap{
		margin:40rpx;
		background: #fff;
		padding-top:10rpx;
		.detail{
			line-height: 40rpx;
			margin:40rpx;
			margin-bottom:10rpx;
			list-style: none;
			.time{
				font-size: 24rpx;
				color:#bebebe;
				
			}
			
		}
		.title{
			font-size: 32rpx;
			font-weight: bold;
		}
		.bottom{
			text-align: right;
			padding:10rpx;
			border-top:1px solid #ddd;
			padding-top:20rpx;
			display: flex;
			justify-content: space-between;
			.ic{
				margin-right:20rpx;
			}
			.right{
				color: #D8A066;	
			}
		}
	}
</style>
