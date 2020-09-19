<template>
	<view class="rules">
		<a-navbar title="公司规章制度" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-collapse  :key='collapseKey'>
				<u-collapse-item :title="item.head" v-for="(item, index) in itemList" :key="index" class="bg-white border-bottom">
					<view class="page-bg border-bottom flex a-center j-between flex-row padding-lr-40 height-line-80"  v-for="(val,i) in item.children"  
						v-if="item.children && item.children.length"	@click="toDetail(item,val)" :key='i'>
						<text>{{val.title}}</text>
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
				collapseKey:0,
				itemList:[
					{
						head:"人事部",
						fenlei:4,
						children:[]
					},{
						head:"行政部",
						fenlei:6,
						children:[]
					},{
						head:"财务部",
						fenlei:7,
						children:[]
					}
				]
			}
		},
		onLoad(){
			this.init()
		},
		methods:{			
			init(){
				let _this=this
				for (let i=0;i<=this.itemList.length-1;i++){
					let item=this.itemList[i]
					_this.getListInfo(item.fenlei,(res)=>{
						_this.itemList[i].children=res
						_this.collapseKey+=1					
					})
				}
			},
			getListInfo(fenlei,callback){			
				this.$tool.uniRequest({
					url:"gongsi/zhishi/",
					method:'GET',					
					params:{
						fenlei:fenlei
					},
					success:(res)=>{
						callback(res)
					}
				})
			},
			toDetail(item,val){
				this.$tool.uniNavigateTo({
					url:`/pages/home/base/rich?fromPage=rules&id=${val.id}`
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
