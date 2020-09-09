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
						info:"2020-08-05",
						arrow:true,
						path:""
					},{
						name:"部门",
						info:"销售部",
						arrow:true,
						path:""
					},{
						name:"职位",
						info:"实习置业顾问",
						arrow:true,
						path:""
					},{
						name:"入职来源",
						info:"xxx-内部推荐",
						arrow:true,
						path:""
					},{
						name:"从业经验",
						info:"暂无",
						arrow:true,
						path:""
					},{
						name:"资格证书编号",
						info:"xxxxxx",
						arrow:false
					}
				]
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
