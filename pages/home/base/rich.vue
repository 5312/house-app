<template>
	<view class="rich">
		<a-navbar :title="navbarTitle" @back="back"></a-navbar>
		<view class="title" v-if="detail">
			{{detail.title || ''}}
		</view>
		<view class="content" v-if="content">
			<u-parse :html="content"></u-parse>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navbarTitle:"详情",
				content: '',
				fromPage:'',
				options:null,
				detail:null
			}
		},
		onLoad(options) {
			this.options=options || null
			console.log(options)
			this.fromPage=options.fromPage || ''
			if(this.fromPage==='rules'){
				this.navbarTitle='制度详情'
				this.getDetail(options.id)
			}
		},
		methods:{
			getDetail(id){			
				this.$tool.uniRequest({
					url:"gongsi/zsxiangxi/",
					method:'GET',					
					params:{
						id
					},
					success:(res)=>{
						this.detail=res
						this.content=res.neirong
						this.content =this.content? this.$tool.htmlre(this.content) : ''
						this.content=this.$tool.formatRichText(this.content)
					}
				})
			},
			back(){
				uni.navigateBack({
				    delta: 1
				});
			}
		}
	}
</script>

<style scoped lang="scss">
.rich{
	.title{
		text-align: center;
		margin-top: 30rpx ;
		font-weight: 600;
		padding: 0 40px;
		font-size: 30rpx;
	}
	.content{
		padding: 30rpx;
	}
}
</style>
