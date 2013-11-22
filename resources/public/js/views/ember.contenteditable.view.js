Ember.ContenteditableView = Em.View.extend(Ember.ViewTargetActionSupport, {
        tagName: 'span',
        attributeBindings: ['contenteditable', 'id'],

        // Variables:
        editable: false,
        isUserTyping: false,
        plaintext: false,

        // Properties:
        contenteditable: (function() {
            var editable = this.get('editable');

            return editable ? 'true' : undefined;
        }).property('editable'),

        // Observers:
        valueObserver: (function() {
            if (!this.get('isUserTyping') && this.get('value')) {
                return this.setContent();
            }

            this.triggerAction({
                action: 'contentEditableFieldDidChange',
                target: this.get('controller'),
                actionContext: {
                    'value' : this.get('value'),
                    'id' : this.$().attr('id')
                }
            });
        }).observes('value'),

        // Events:
        didInsertElement: function() {
            return this.setContent();
        },

        focusOut: function() {
            return this.set('isUserTyping', false);
        },

        keyDown: function(event) {
            if (!event.metaKey) {
                return this.set('isUserTyping', true);
            }
        },

        keyUp: function(event) {
            if (this.get('plaintext')) {
                return this.set('value', this.$().text());
            } else {
                return this.set('value', this.$().html());
            }
        },
        setContent: function() {
            return this.$().html(this.get('value'));
        }
});