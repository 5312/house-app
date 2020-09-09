<template>
	<view class="rules">
		<a-navbar title="公司规章制度" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-collapse>
				<u-collapse-item :title="item.head" v-for="(item, index) in itemList" :key="index" class="bg-white border-bottom" @change='collapseChange'>
					<view class="page-bg border-bottom flex a-center j-between flex-row padding-lr-40 height-line-80"  v-for="(val,i) in itemListChildren" 
						@click="toDetail(item)" :key='i'>
						<text>{{val.name}}</text>
						<u-icon name="arrow-right" color="#c8c9cc"></u-icon>
					</view>
				</u-collapse-item>
			</u-collapse>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				itemListChildren:[],
				itemList:[
					{
						head:"人事部",
						fenlei:4
					},{
						head:"行政部",
						fenlei:6
					},{
						head:"财务部",
						fenlei:7
					}
				]
			}
		},
		onLoad(){
			this.getListInfo()
		},
		methods:{
			collapseChange(val){
				console.log(val)
			},
			getListInfo(){
				this.$tool.uniRequest({
					url:"gongsi/zhishi/",
					method:'GET',					
					params:{
						fenlei:6
					},
					success:(res)=>{
						this.itemListChildren=[]
					}
				})
			},
			toDetail(item){
				this.$tool.uniNavigateTo({
					url:"/pages/home/base/rich"
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.rules{
		.content{
			/deep/.u-collapse-head{
				padding-left: 30rpx;
				padding-right: 30rpx;
			}
		}
	}
</style>
