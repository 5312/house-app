<template>
	<view class="entry-info min-height-100 page-bg">	
		<a-navbar title="入职信息" @back="$tool.uniRedirectTo({url:'/pages/home/personalInfo/index'})"></a-navbar>
		<view class="content">
			<u-gap height="12"></u-gap>
			<template v-for="(item,index) in list">
				<a-section :item="item" :key='index' :class="[index !==3 && index !==7 &&'border-bottom']"
					 @select="select" :isArrow='item.arrow'></a-section>				
				<u-gap height="10" v-if="index ==2"></u-gap>
			</template>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				list:[
					{
						name:"入职时间",
						info:"",
						arrow:false,
						prop:"rzshijian",
						path:""
					},{
						name:"部门",
						info:"",
						arrow:false,
						prop:'bumen',
						path:""
					},{
						name:"职位",
						info:"",
						arrow:false,
						prop:'gangwei',
						path:""
					},{
						name:"推荐人",
						info:"",
						arrow:false,
						prop:'daoshi',
						path:""
					},{
						name:"转正时间",
						info:"",
						arrow:false,
						prop:'zztime',
						path:""
					},{
						name:"投保状态",
						info:"",
						prop:'tbzhuangtai',
						arrow:false
					},{
						name:"合同状态",
						info:"",
						prop:'htzhuangtai',
						arrow:false
					}
				]
			}
		},
		onLoad(){
			this.init()
		},
		methods:{
			init(){
				this.$tool.uniRequest({
					url:"rsdangan/ruzhi/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId')
					},
					success:(res)=>{
						this.list.forEach(item=>{
							for(let i in res){
								if(item.prop==i){
									item.info=res[i]
								}
							}
						})
					}
				})	
			}
		}
	}
</script>

<style scoped lang="scss">
.entry-info{
	.header{
		height: 240rpx;
		.name{
			margin-top: 14rpx;
			font-size: 30rpx;
		}
	}
	.content{
		.line{
			height: 80rpx;
			padding: 0 20rpx;
		}
	}
}
</style>
