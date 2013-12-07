var attr = DS.attr;
Tracula.Ticket = DS.Model.extend({
	cc: attr('string'),
	reporter: DS.attr('string'),
	blockedby: DS.attr('string'),
	priority: DS.attr('string'),
	time_changed: DS.attr('string'),
	summary: DS.attr('string'),
	keywords: DS.attr('string'),
	resolution: DS.attr('string'),
	milestone: DS.attr('string'),
	status: DS.attr('string'),
	owner: DS.attr('string'),
	blocking: DS.attr('string'),
	due_date: DS.attr('string'),
	time_created: DS.attr('string'),
	type: DS.attr('string'),
	description: DS.attr('string'),
	component: DS.attr('string'),
	_ts: DS.attr('string')
});