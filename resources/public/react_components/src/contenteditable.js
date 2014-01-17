var ContentEditableDiv  = React.createClass( {
    getInitialState : function() {
        return {
            changed : false,
            isUserTyping : false
        }
    },
    focusOut: function() {
        return this.setState({'isUserTyping' : false});
    },
    keyDown: function(event) {
        if (!event.metaKey) {
            return this.set('isUserTyping', true);
        }
    },
    keyUp: function(event) {
        if (this.get('value')) {
            return this.set('value', this.$().text());
        } else {
            return this.set('value', this.$().html());
        }
    },
    render : function() {
        var className = '';
        var content = '';
        return (<div className="{className}" id="{id}" contentEditable="true">{content}</div>)
    }
});
