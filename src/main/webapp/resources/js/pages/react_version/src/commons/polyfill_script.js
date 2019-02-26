/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 *
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;
// poly fill for object.observe using proxy;
(function () {
    'use strict';
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/observe
    var observe = function (obj, callback) {
        if (Object(obj) !== obj) {
            throw new TypeError('target must be an Object, given ' + obj);
        }
        if (typeof callback !== 'function') {
            throw 'observer must be a function, given ' + callback;
        }
        return new Proxy(obj, {
            set: function (target, propKey, value, receiver) {
                var oldVal = target[propKey];
                // Don't send change record if value didn't change.
                if (oldVal === value) {
                    return;
                }
                var type = oldVal === undefined ? 'add' : 'update';
                var changeRecord = {
                    name: propKey,
                    type: type,
                    object: target
                };
                if (type === 'update') {
                    changeRecord.oldValue = oldVal;
                }
                target[propKey] = value; // set prop value on target.
                callback([changeRecord]);
            },
            get: function (obj, prop) {
                // An extra property
                if (prop === 'latestBrowser') {
                    return obj.browsers[obj.browsers.length - 1];
                }
                // The default behavior to return the value
                return obj[prop];
            },
            deleteProperty: function (target, propKey, receiver) {
                // Don't send change record if prop doesn't exist.
                if (!(propKey in target)) {
                    return;
                }
                var changeRecord = {
                    name: propKey,
                    type: 'delete',
                    object: target,
                    oldValue: target[propKey]
                };
                delete target[propKey]; // remove prop from target.
                callback([changeRecord]);
            }
        });
    };
    if (!Object.observe) {
        Object.observe = observe;
    }
})();
