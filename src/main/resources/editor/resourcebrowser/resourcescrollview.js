steal.plugins('jquery/view/tmpl', 'jqueryui/widget')
.views('//editor/resourcebrowser/views/resourcescrollview.tmpl')
.resources('jquery.smoothDivScroll-1.1', 'jquery.lazyload')
.css('resources/smoothDivScroll')
.then('resourceview', 'resourcescrollviewitem')
.then(function($) {
	
	Editor.Resourceview.extend('Editor.Resourcescrollview', 
	{
		init: function(el) {
			$(el).html('//editor/resourcebrowser/views/resourcescrollview.tmpl', {resourceType: this.options.resourceType});
			this._initViewItems();
			this._initButtons();
			this._initFilter();
			this._initDialogs();
			this._initSmoothDivScroll();
			this._initLazyLoading();
		},
		
		update: function(options) {
			this._super(options);
			this.find('div.wbl-scrollViewItem').remove();
			this.divScroll.smoothDivScroll("recalculateScrollableArea");
			this.divScroll.smoothDivScroll("hideHotSpotBackgrounds");
			if($.isEmptyObject(this.options.resources)) return;
			this._initViewItems();
			this.divScroll.smoothDivScroll("recalculateScrollableArea");
			this.divScroll.smoothDivScroll("showHotSpotBackgrounds");
			this.divScroll.smoothDivScroll("startAutoScroll");
			this._initLazyLoading();
		},
		
		_initSmoothDivScroll: function() {
			this.divScroll = this.find('#wbl-makeMeScrollable').smoothDivScroll({
			  	autoScroll: "onstart",
				autoScrollDirection: "left",
				autoScrollStep: 1,
				autoScrollInterval: 15,
				visibleHotSpots: "always"
		  	});
		},
		
		_initLazyLoading: function() {
			var rootPath = this.options.runtime.getRootPath();
			this.element.find('img.wbl-pageThumbnail').lazyload({
				placeholder: rootPath + "/editor/resourcebrowser/images/empty_thumbnail.png",
				event: "scroll",
				container: this.element.find("div.scrollWrapper")
			}).one("error", function() {
				$(this).hide().attr('src', rootPath + '/editor/resourcebrowser/images/empty_thumbnail.png').show();
			});
		},
		
		_initViewItems: function() {
			$.each(this.options.resources, $.proxy(function (i, res) {
				var scrollViewItem = this.element.find('div.scrollableArea').append('//editor/resourcebrowser/views/resourcescrollviewitem.tmpl', {
					page: res, 
					language: this.options.language,
					runtime: this.options.runtime,
					resourceType: this.options.resourceType
				});
				scrollViewItem.find('div.wbl-scrollViewItem:last').editor_resourcescrollviewitem({
					page: res, 
					runtime: this.options.runtime, 
					language: this.options.language,
					resourceType: this.options.resourceType,
					mode: this.options.mode
				});
			}, this));
		},
		
		"button.wbl-duplicate click": function(el, ev) {
			this.options.selectedResources = this.find('div.wbl-scrollViewItem.wbl-marked');
			if(this.options.selectedResources.length == 1) {
				this.duplicateDialog.dialog('open');
			} else if(this.options.selectedResources.length > 1) {
				this.element.trigger('showMessage', 'Es kann nur eine Seite markiert werden.');
			} else {
				this.element.trigger('showMessage', 'Es wurde keine Seite markiert.');
			}
		},
		
		"button.wbl-delete click": function(el, ev) {
			this.options.selectedResources = this.find('div.wbl-scrollViewItem.wbl-marked');
			if(this.options.selectedResources.length) {
				this.deleteDialog.dialog('open');
			} else {
				this.element.trigger('showMessage', 'Es wurde keine Seite markiert.');
			}
		},
		
		"button.wbl-favorize click": function(el, ev) {
			this.options.selectedResources = $('div.wbl-scrollViewItem.wbl-marked');
			this.element.trigger('favorizeResources', this.options.selectedResources);
		}
		
	});

});
