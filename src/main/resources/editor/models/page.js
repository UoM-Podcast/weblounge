steal.then('jsonix')
.then(function($) {
	
	$.Model('Page',
	/* @Static */
	{
		/**
		 * Get Page from Path or Id
		 * @param {Object} params path or id
		 */
		findOne: function(params, success, error) {
			if ('path' in params) {
				$.ajax('/system/weblounge/pages?details=true&path=' + params.path, {
					success: this.callback(['parseXMLPage', 'wrap', success]),
				});
			} 
			else if ('id' in params) {
				$.ajax('/system/weblounge/pages/' + params.id, {
					success: this.callback(['parseXMLPage','wrap', success]),
				});
			}
		},
		
		/**
		 * Get all Pages
		 */
		findAll: function(params, success, error) {
			$.ajax('/system/weblounge/pages/?sort=created-asc&limit=0&offset=0', {
				success: function(xml) {
					var json = Page.parseXML(xml);
					success(json.value.page);
				}
			});
		},
		
		/**
		 * Get Recent Pages
		 */
		findRecent: function(params, success, error) {
			$.ajax('/system/weblounge/pages/?sort=modified-desc&limit=8&offset=0', {
				success: function(xml) {
					var json = Page.parseXML(xml);
					success(json.value.page);
				}
			});
		},
		
		/**
		 * Updates the specified page.
		 * @param {Object} params The page identifier
		 * @param {Page} page The page object
		 */
		update: function(params, page, success, error){
			if ('id' in params) {
				$.ajax({
					url: '/system/weblounge/pages/' + params.id,
					type: 'put',
					dataType: 'xml',
					data: {content : Page.parseJSON(page)}
				});
			}	
		},
		
		/**
		 * Creates a new page, either at the given path or at a random location and returns the REST url of the created resource.
		 * @param {Object} params The target path
		 * @param {Page} page The page data
		 */
		create: function(params, page, success, error){
			if ('path' in params) {
				$.ajax({
					url: '/system/weblounge/pages/',
					type: 'post',
					dataType: 'xml',
					data: {path : params.path, content : Page.parseJSON(page)}
				});
			}	
		},
		
		/**
		 * Deletes the specified page.
		 * @param {Object} params The page identifier 
		 */
		destroy: function(params, success, error){
			if ('id' in params) {
				$.ajax({
					url: '/system/weblounge/pages/' + params.id,
					type: 'delete'
				});
			}
		},
		
		/**
		 * Converts XML to JSON
		 */
		parseXML: function(xml) {
			var unmarshaller = Editor.Jsonix.context().createUnmarshaller();
			return unmarshaller.unmarshalDocument(xml);
		},
		
		/**
		 * Converts XML to JSON
		 */
		parseXMLPage: function(xml) {
			var page = $(xml).find('page')[0];
			var unmarshaller = Editor.Jsonix.context().createUnmarshaller();
			return unmarshaller.unmarshalDocument(page);
		},
		
		/**
		 * Converts JSON to XML
		 */
		parseJSON: function(json) {
			var marshaller = Editor.Jsonix.context().createMarshaller();
			return marshaller.marshalString(json);
		}

	},
	/* @Prototype */
	{
		/**
		 * Return the specified Composer
		 * @param {String} composerId
		 */
	    getComposer: function(id) {
	    	var composer;
	    	$.each(this.value.body.composers, function(i, comp) {
	    		if(comp.id == id) {
	    			composer = comp;
	    			return false;
	    		}
    		});
	    	return composer;
	    },
	    
	    /**
	     * Return the specified Composer Index
	     * @param {String} composerId
	     */
	    getComposerIndex: function(id) {
	    	var index = -1;
	    	$.each(this.value.body.composers, function(i, composer) {
	    		if(composer.id == id) {
	    			index = i;
	    			return false;
	    		};
    		});
	    	return index;
	    },
	    
	    deletePagelet: function(composerId, index){
	    	var composer = this.getComposer(composerId);
	    	composer.pagelets.splice(index, 1);
	    	Page.update({id:this.value.id}, this);
	    },
 	    
	    /**
	     * Return the specified Pagelet
	     * @param {String} composerId
	     * @param {int} index
	     */
	    getPagelet: function(composerId, index) {
	    	var composer = this.getComposer(composerId);
	    	return composer.pagelets[index];
	    },
	    
	    /**
	     * Changes current Composer with new one and Update in Repository
	     * @param {String} composerId Composer to change
	     * @param {Object} newComposer New Composer Object
	     */
	    updateComposer: function(composerId, newComposer) {
	    	var index = this.getComposerIndex(composerId);
	    	this.value.body.composers[index].pagelets = newComposer;
	    	Page.update({id:this.value.id}, this);
	    },
	    
	    /**
	     * Return Pagelet for Pagelet-Editor with current and original Language
	     * @param {String} composerId
	     * @param {int} index
	     * @param {String} language
	     */
	    getEditorPagelet: function(composerId, index, language) {
	    	var pagelet = this.getPagelet(composerId, index);
	    	var copyPagelet = jQuery.extend(true, {}, pagelet);

			$.each(copyPagelet.locale, function(i, locale) {
				if(locale.language == language) {
					copyPagelet.locale.current = locale;
				}
				if(locale.original == true) {
					copyPagelet.locale.original = locale;
				}
			});
			return copyPagelet;
	    },
	    
	    /**
	     * Insert Pagelet to the specified position in the composer, this removes the current pagelet at that position
	     * @param {Object} pagelet The pagelet to insert
	     * @param {String} composerId The parent composer id 
	     * @param {int} index The pagelet index to insert
	     */
	    insertPagelet: function(pagelet, composerId, index) {
	    	delete pagelet.locale.current;
	    	delete pagelet.locale.original;
	    	this.value.body.composers[this.getComposerIndex(composerId)].pagelets[index] = pagelet;
	    	Page.update({id:this.value.id}, this);
	    }
	    
	});

});
