<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "uview-ui/index.scss";
	@import url("common/css/index.css");
	.a-section{
		padding:0 30rpx ;
		.left{
			.icon{
				margin-right: 14rpx;
				position: relative;
				top: -2rpx;
			}
		}
	}
	.active{
		opacity: 0.8;
	}
</style>
