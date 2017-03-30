function validateOnChecked(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oConditionFields = new validateonchecked();

						// Start: Loop
						for (x in oConditionFields) 
						{
							var field = form[oConditionFields[x][0]];
							if(field == null)
								continue;
							
							var secondFields = oConditionFields[x][2]("secondProperty").split(",");
							var secondField;
							var secondFieldValues = oConditionFields[x][2]("secondPropertyValue").split(",");
							var secondFieldValue;		
							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password')
							{
								// Start: Inner Loop 1
								for	(var h=0;h<secondFields.length;h++)
								{
									secondField=form(secondFields[h]);
									secondFieldValue=secondFieldValues[h];
									var value  ;
									var secondValue;
									// get field's value
									if (field.type == "select-one")
									{
										var si = field.selectedIndex;
										value = field.options[si].value;
										secondValue = secondField.options[si].value;
									} 
									else if ((secondField.length > 0) && secondField[0].type == 'radio')
									{
										value = field.value;

										// Start: Inner Loop 2
										for(var k = 0; k < secondField.length; k++) 
										{
											if(secondField[k].checked) 
											{
												secondValue = secondField[k].value;
											}
										}
										// End: Inner Loop 2
									}
									else
									{
										value = field.value;
										secondValue = secondField.value;
									}
									if (secondValue == secondFieldValue)
									{
										if (trim(value).length == 0) 
										{
											bValid = false;
											if (i == 0) 
											{
												focusField = field;
											}
										}
									}
									else
									{
										bValid = true;
										break;
									}

								}
								// End: Inner Loop 1

								if(!bValid)
									fields[i++] = oConditionFields[x][1];
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}

						return bValid;
					}
function validateByte(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oByte = new ByteValidations();
						
						// Start: Loop
						for (x in oByte)
						{
							var field = form[oByte[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio')
							{
								var value = '';
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									if (si >= 0)
									{
										value = field.options[si].value;
									}
								}
								else
								{
									value = field.value;
								}

								if (value.length > 0)
								{
									if (!isAllDigits(value, false))
									{
										bValid = false;
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oByte[x][1];
									}
									else
									{
										var iValue = parseInt(value, 10);
										if (isNaN(iValue) || !(iValue >= -128 && iValue <= 127))
										{
											if (i == 0)
											{
												focusField = field;
											}
											fields[i++] = oByte[x][1];
											bValid = false;
										}
									}
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateDecimals(form) 
					{
						var bValid = true;
						var focusField = null;
						var nIndex = 0;
						var i = 0;
						var ctr=0
						var fields = new Array();

						oDecimals = new decimals();

						// Start: Loop
						for (x in oDecimals) 
						{
							var isValid = true;
							var field = form[oDecimals[x][0]];

							if(field == null)
								continue;

							var value = field.value;

							var decimalCount = oDecimals[x][2]("COUNT");

							if(decimalCount == undefined)
								decimalCount = DECIMAL_COUNT;

							if ((field.type == 'text' || field.type == 'textarea') && (trim(value).length > 0)) 
							{
								nIndex = value.indexOf('.'); 
								if(nIndex > -1)
								{
									var subtstr = value.substring(nIndex + 1, value.length);
									if(subtstr.length > decimalCount)
									isValid = false;
								}

							}

							if(!isValid)
							{
								focusField = field;
								fields[i++] = oDecimals[x][1];
								bValid = false;
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateNumeric(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oNumeric = new numeric();
						
						// Start: Loop
						for (x in oNumeric) 
						{
		
							if(!clearDependency(form, oNumeric, x))
								continue;
		
							var field = form[oNumeric[x][0]];
							if(field == null)
		                		continue;
							
							var fieldValue = field.value;
		
							if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
								var value = '';
		
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else
								{
									value = field.value;
								}
		
								if (value.length > 0)
								{
									if(!chkNumeric(value)) 
										bValid = false;
									else
									{
										var iValue = parseFloat(value);
										
										if (isNaN(iValue)) 
											bValid = false;
										else if (iValue <= MIN_VALUE) 
												bValid = false;
									}
								}
		
								if(!bValid)
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oNumeric[x][1];
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateDate(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oDate = new DateValidations();

						// Start: Loop
						for (x in oDate)
						{

							if(form[oDate[x][0]] == null)
								continue;

							var value = form[oDate[x][0]].value;
							var datePattern = oDate[x][2]("datePatternStrict");
							if ((form[oDate[x][0]].type == 'text' || form[oDate[x][0]].type == 'textarea') && (value.length > 0) && (datePattern.length > 0))
							{
								var MONTH = "MM";
								var DAY = "dd";
								var YEAR = "yyyy";
								var orderMonth = datePattern.indexOf(MONTH);
								var orderDay = datePattern.indexOf(DAY);
								var orderYear = datePattern.indexOf(YEAR);
								if ((orderDay < orderYear && orderDay > orderMonth))
								{
									var iDelim1 = orderMonth + MONTH.length;
									var iDelim2 = orderDay + DAY.length;
									var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
									var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
									if (iDelim1 == orderDay && iDelim2 == orderYear)
									{
										dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
									}
									else if (iDelim1 == orderDay)
									{
										dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
									}
									else if (iDelim2 == orderYear)
									{
										dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
									}
									else
									{
										dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
									}
									var matched = dateRegexp.exec(value);
									if(matched != null)
									{
										if (!isValidDate(matched[2], matched[1], matched[3]))
										{
											if (i == 0)
											{
												focusField = form[oDate[x][0]];
											}
											fields[i++] = oDate[x][1];
											bValid =  false;
										}
									}
									else
									{
										if (i == 0)
										{
											focusField = form[oDate[x][0]];
										}
										fields[i++] = oDate[x][1];
										bValid =  false;
									}
								}
								else if ((orderMonth < orderYear && orderMonth > orderDay))
								{
									var iDelim1 = orderDay + DAY.length;
									var iDelim2 = orderMonth + MONTH.length;
									var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
									var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
									if (iDelim1 == orderMonth && iDelim2 == orderYear)
									{
										dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
									}
									else if (iDelim1 == orderMonth)
									{
										dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
									}
									else if(iDelim2 == orderYear)
									{
										dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
									}
									else
									{
										dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
									}
									var matched = dateRegexp.exec(value);
									if(matched != null)
									{
										if (!isValidDate(matched[1], matched[2], matched[3]))
										{
											if (i == 0)
											{
												focusField = form[oDate[x][0]];
											}
											fields[i++] = oDate[x][1];
											bValid =  false;
										}
									}
									else
									{
										if (i == 0)
										{
											focusField = form[oDate[x][0]];
										}
										fields[i++] = oDate[x][1];
										bValid =  false;
									}
								}
								else if ((orderMonth > orderYear && orderMonth < orderDay))
								{
									var iDelim1 = orderYear + YEAR.length;
									var iDelim2 = orderMonth + MONTH.length;
									var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
									var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
									if (iDelim1 == orderMonth && iDelim2 == orderDay)
									{
										dateRegexp = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
									}
									else if (iDelim1 == orderMonth)
									{
										dateRegexp = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
									}
									else if (iDelim2 == orderDay)
									{
										dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
									}
									else
									{
										dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
									}
									var matched = dateRegexp.exec(value);
									if(matched != null)
									{
										if (!isValidDate(matched[3], matched[2], matched[1]))
										{
											if (i == 0)
											{
												focusField = form[oDate[x][0]];
											}	
											fields[i++] = oDate[x][1];
											bValid =  false;
										}
									}
									else
									{
										if (i == 0)
										{
											focusField = form[oDate[x][0]];
										}
										fields[i++] = oDate[x][1];
										bValid =  false;
									}
								}
								else
								{
									if (i == 0)
									{
										focusField = form[oDate[x][0]];
									}
									fields[i++] = oDate[x][1];
									bValid =  false;
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}

					function isValidDate(day, month, year)
					{
						if (month < 1 || month > 12)
						{
							return false;
						}
						if (day < 1 || day > 31)
						{
							return false;
						}
						if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
						{
							return false;
						}
						if (month == 2)
						{
							var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
							if (day>29 || (day == 29 && !leap))
							{
								return false;
							}
						}
						return true;
					}
function validateCreditCard(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oCreditCard = new creditCard();

						// Start: Loop
						for (x in oCreditCard)
						{
							if(form[oCreditCard[x][0]] == null)
								continue;

							if ((form[oCreditCard[x][0]].type == 'text' || form[oCreditCard[x][0]].type == 'textarea') && (form[oCreditCard[x][0]].value.length > 0))
							{
								if (!luhnCheck(form[oCreditCard[x][0]].value))
								{
									if (i == 0)
									{
										focusField = form[oCreditCard[x][0]];
									}
									fields[i++] = oCreditCard[x][1];
									bValid = false;
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}

					/**
					* Reference: http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
					*/
					function luhnCheck(cardNumber)
					{
						if (isLuhnNum(cardNumber))
						{
							var no_digit = cardNumber.length;
							var oddoeven = no_digit & 1;
							var sum = 0;

							// Start: Loop
							for (var count = 0; count < no_digit; count++)
							{
								var digit = parseInt(cardNumber.charAt(count), 10);
								if (!((count & 1) ^ oddoeven))
								{
									digit *= 2;
									if (digit > 9) digit -= 9;
								};
								sum += digit;
							};
							// End: Loop

							if (sum == 0) return false;
							if (sum % 10 == 0) return true;
						};
						return false;
					}

					function isLuhnNum(argvalue)
					{
						argvalue = argvalue.toString();
						if (argvalue.length == 0)
						{
							return false;
						}

						// Start: Loop
						for (var n = 0; n < argvalue.length; n++)
						{
							if ((argvalue.substring(n, n+1) < "0") || (argvalue.substring(n,n+1) > "9"))
							{
								return false;
							}
						}
						// End: Loop

						return true;
					}
function validateNotContains(form) 
		            {
		               var bValid = true;
		               var focusField = null;
		               var nIndex = 0;
		               var i = 0;
		               var ctr=0
		               var fields = new Array();
						
		               oList = new notContains();
		               
					   // Start: Loop
		               for (x in oList) 
		               {
						   if(!clearDependency(form, oList, x))
						   		continue;
								
		               	   var isValid = true;
		               	   var field = form[oList[x][0]];
		               	   
		               	   if(field == null)
		                		continue;
		                		
		                   var value = "";
		                   
		                   var list = oList[x][2]("exclude");
		
		                   if ((field.type == 'text' || field.type == 'textarea' || field.type == 'password' || field.type == "select-one") && 
		                   		(trim(list).length > 0)) 
		                  {
		                  
		                  	if (field.type == "select-one") 
							{
								var si = field.selectedIndex;
								if (si >= 0) 
								{
									value = field.options[si].value;
								}
							} 
							else
							{
								value = field.value;
							}
							
							if(trim(value).length > 0)
							{
		                 
			                  	var values = list.split(",");
			                  	listSize = values.length;
			                  	
								// Start: Inner Loop
			                  	for(ctr=0; ctr<listSize; ctr++)
			                	{
			                		if(trim(values[ctr]) == 'comma')
			                			nIndex = value.indexOf(',');
			                		else if(trim(values[ctr]) == 'bslash')
			                			nIndex = value.indexOf('\\');
			                		else if(trim(values[ctr]) == 'fslash')
			                			nIndex = value.indexOf('/');
			                		else if(trim(values[ctr]) == 'squote')
			                			nIndex = value.indexOf("'");
			                		else if(trim(values[ctr]) == 'dquote')
			                			nIndex = value.indexOf('"');
			                		else if(trim(values[ctr]) == 'space')
			                			nIndex = value.indexOf(' ');
			                		else
			                			nIndex = value.indexOf(trim(values[ctr]));
			                		isValid = nIndex < 0;
			
			                		if(!isValid)
			                			break;
			                	}
								// End: Inner Loop
							}
		                     
		                  }
		                  
		                  if(!isValid)
		                  {
		                  	focusField = field;
		                  	fields[i++] = oList[x][1];
		                  	bValid = false;
		                  }
		                  
		                  if(!bValid)
							break;
		               }
					   // End: Loop
		
		               if (fields.length > 0) 
		               {
		                   focusField.focus();
		                   alert(fields.join('\n'));
		               }
		               return bValid;
		            }
function validateShkPhone(form) 
					{
						var isValid = true;
						var focusField = null;
						var numchar="0123456789";
						var telechar=numchar+"()- ";
						var i = 0;
						var fields = new Array();
						oPhone = new shkphone();

						// Start: Loop
						for (x in oPhone) 
						{

							if(!clearDependency(form, oPhone, x))
								continue;

							var field = form[oPhone[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea') 
							{
								var value = '';
								// get field's value
								value = field.value;
								teststr=new String(value);

								// Start: Inner Loop
								for(k=0;k<teststr.length;k++)
								{
									if(telechar.indexOf(teststr.charAt(k))==-1)
									{
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oPhone[x][1];
										isValid = false;
										break;
									}
								}
								// End: Inner Loop
							}
							
							if(!isValid)
								break;
						}
						// End: Loop

						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}
function validateId(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var numchar="0123456789";
						var alphachar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
						var hkidchar=alphachar+numchar;
						var teststr="";
						var fields = new Array();
						oIdFields = new hongkongid();

						// Start: Loop
						for (x in oIdFields) 
						{
							if(!clearDependency(form, oIdFields, x))
								continue;
							var field = form[oIdFields[x][0]];

							if(field == null)
								continue;

							var secondField = form[oIdFields[x][2]("secondProperty")];
							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password')
							{
								var value;
								var secondValue;
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									value = field.options[si].value;
									secondValue = secondField.options[si].value;
								} 
								else 
								{
									value = field.value;
									for(var k = 0; k < secondField.length; k++) 
									{
										if(secondField[k].checked)
										{
											secondValue = secondField[k].value;
										}
									}
								}
								if(secondValue == 'H')
								{

									if (value.length != 7)
									{
										if (i == 0) 
										{
											focusField = field;
										}
										fields[i++] = oIdFields[x][1];
										bValid = false;
									}
									else 
									{
										teststr=new String(value.toUpperCase());
										for(j=0;j<teststr.length;j++)
										{
											if(hkidchar.indexOf(teststr.charAt(j))== -1)
											{
												if (i == 0)
												{
													focusField = field;
												}
												fields[i++] = oIdFields[x][1];
												bValid = false;
											}
										}
									}
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}

						return bValid;
					}
function validateTwoFields(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oTwoFields = new twofields();

						// Start: Loop
						for (x in oTwoFields)
						{
							var field = form[oTwoFields[x][0]];
							if(field == null)
								continue;

							var secondField = form[oTwoFields[x][2]("secondProperty")];

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password')
							{
								var value;
								var secondValue;
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									value = field.options[si].value;
									secondValue = secondField.options[si].value;
								}
								else
								{
									value = field.value;
									secondValue = secondField.value;
								}

								if (value != secondValue)
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oTwoFields[x][1];
									bValid = false;
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}

						return bValid;
					}
function validateLong(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oLong = new longType();

						// Start: Loop
						for (x in oLong)
						{
							if(!clearDependency(form, oLong, x))
								continue;

							var field = form[oLong[x][0]];
							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio')
							{
								var value = '';
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									if (si >= 0)
									{
										value = field.options[si].value;
									}
								}
								else
								{
									value = field.value;
								}

								if (value.length > 0)
								{
									if (!isAllDigits(value, false)) 
									{
										bValid = false;
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oLong[x][1];

									}
									else
									{
										var iValue = parseInt(value, 10);
										if (isNaN(iValue) || !(iValue >= -9223372036854775808 && iValue <= 9223372036854775807))
										{
											if (i == 0)
											{
												focusField = field;
											}
											fields[i++] = oLong[x][1];
											bValid = false;
										}
									}
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}

					function isAllDigits(argvalue, bIncludeDecimal)
					{
						argvalue = argvalue.toString();
						var validChars = "";

						if(bIncludeDecimal)
							validChars = "0123456789.";
						else
							validChars = "0123456789";
						var startFrom = 0;
						
						// Start: Loop
						for (var n = startFrom; n < argvalue.length; n++)
						{
							if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
						}
						// End: Loop

						return true;
					}
function validateNotContainsField(form) 
		            {
		               var bValid = true;
		               var focusField = null;
		               var nIndex = 0;
		               var i = 0;
		               var ctr=0
		               var fields = new Array();
						
		               oList = new notContainsField();
		               
					   // Start: Loop
		               for (x in oList) 
		               {
		               	   var isValid = true;
		               	   var field = form[oList[x][0]];
		               	   
		               	   if(field == null)
		                		continue;
		                		
		                   var value = "";
		                   
		                   var list = oList[x][2]("exclude");
		
		                   if ((field.type == 'text' || field.type == 'textarea' || field.type == 'password' || field.type == "select-one") && 
		                   		(trim(list).length > 0)) 
		                  {
		                  
		                  	if (field.type == "select-one") 
							{
								var si = field.selectedIndex;
								if (si >= 0) 
								{
									value = field.options[si].value;
								}
							} 
							else
							{
								value = field.value;
							}
							
							if(trim(value).length > 0)
							{
		                 
			                  	var values = list.split(",");
			                  	listSize = values.length;
			                  	
								// Start: Inner Loop
			                  	for(ctr=0; ctr<listSize; ctr++)
			                	{
			                		if(form[values[ctr]])
			                		{
			                			nIndex = value.indexOf(form[values[ctr]].value);
			                			isValid = nIndex < 0;
			                		}

			                		if(!isValid)
			                			break;
			                	}
								// End: Inner Loop
							}
		                     
		                  }
		                  
		                  if(!isValid)
		                  {
		                  	focusField = field;
		                  	fields[i++] = oList[x][1];
		                  	bValid = false;
		                  }
		                  
		                  if(!bValid)
							break;
		               }
					   // End: Loop
		
		               if (fields.length > 0) 
		               {
		                   focusField.focus();
		                   alert(fields.join('\n'));
		               }
		               return bValid;
		            }
function validateFloat(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oFloat = new FloatValidations();

						// Start: Loop
						for (x in oFloat)
						{
							var field = form[oFloat[x][0]];
							if(field == null)
								continue;
							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio')
							{
								var value = '';
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									if (si >= 0)
									{
										value = field.options[si].value;
									}
								}
								else
								{
									value = field.value;
								}

								if (value.length > 0)
								{
									// remove '.' before checking digits
									var tempArray = value.split('.');
									var joinedString= tempArray.join('');

									if (!isAllDigits(joinedString, false))
									{
										bValid = false;
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oFloat[x][1];

									}
									else
									{
										var iValue = parseFloat(value);
										if (isNaN(iValue))
										{
											if (i == 0)
											{
												focusField = field;
											}
											fields[i++] = oFloat[x][1];
											bValid = false;
										}
									}
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateCheckZero(form) 
		            {
		            	var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oCheckZero = new checkZero();

						// Start: Loop
						for (x in oCheckZero) 
						{
							if(!clearDependency(form, oCheckZero, x))
								continue;
								
							var field = form[oCheckZero[x][0]];
							if(field == null)
								continue;

							if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
								var value = '';
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else
								{
									value = field.value;
								}
								
								if (value.length > 0) 
								{
									var iValue = parseFloat(value);
									if (isNaN(iValue)) 
										bValid = false;
									if(bValid && iValue == 0)
										bValid = false;
									else
										bValid = true;
								}
								
								if(!bValid)
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oCheckZero[x][1];
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
		                
		            }
function validateCheckNotInRange(form) 
		            {
		            	var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oCheckNotInRange = new checkNotInRange();

						// Start: Loop
						for (x in oCheckNotInRange) 
						{
							if(!clearDependency(form, oCheckNotInRange, x))
								continue;
								
							var field = form[oCheckNotInRange[x][0]];
							if(field == null)
								continue;
							
							var nMax = oCheckNotInRange[x][2]("MAX");								
							var nMin = oCheckNotInRange[x][2]("MIN");
							
							if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
								var value = '';
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else
								{
									value = field.value;
								}
								
								if (value.length > 0) 
								{
									var iValue = parseFloat(value);
									if (isNaN(iValue)) 
										bValid = false;
									else
									{
										if(nMax != undefined && nMin != undefined)
										{
											var iMax = parseFloat(nMax);
											var iMin = parseFloat(nMin);
											if(iValue < iMax && iValue >= iMin)
												bValid = false;
										}
									} 
								}
								
								if(!bValid)
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oCheckNotInRange[x][1];
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
		                
		            }
function validateCheckNegative(form) 
		            {
		            	var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oCheckNegative = new checkNegative();

						// Start: Loop
						for (x in oCheckNegative) 
						{
							if(!clearDependency(form, oCheckNegative, x))
								continue;
								
							var field = form[oCheckNegative[x][0]];
							if(field == null)
								continue;

							if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
								var value = '';
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else
								{
									value = field.value;
								}
								
								if (value.length > 0) 
								{
									var iValue = parseFloat(value);
									if (isNaN(iValue)) 
										bValid = false;
									if(bValid && iValue < 0)
										bValid = false;
									else
										bValid = true;
								}
								
								if(!bValid)
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oCheckNegative[x][1];
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
		                
		            }
function validateMinLength(form) 
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oMinLength = new minlength();
						
						// Start: Loop
						for (x in oMinLength) 
						{

							if(!clearDependency(form, oMinLength, x))
								continue;

							var field = form[oMinLength[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'password' )
							{
								var iMin = parseInt(oMinLength[x][2]("minlength"));
								if ((trim(field.value).length > 0) && (field.value.length < iMin))
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oMinLength[x][1];
									isValid = false;
								}
							}
							
							if(!isValid)
								break;
						}
						// End: Loop
						
						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}
function validateFloatRange(form)
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oRange = new floatRange();

						// Start: Loop
						for (x in oRange)
						{
							var field = form[oRange[x][0]];

							if(field == null)
								continue;

							if ((field.type == 'text' || field.type == 'textarea') && (field.value.length > 0))
							{
								var fMin = parseFloat(oRange[x][2]("min"));
								var fMax = parseFloat(oRange[x][2]("max"));
								var fValue = parseFloat(field.value);
								if (!(fValue >= fMin && fValue <= fMax))
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oRange[x][1];
									isValid = false;
								}
							}
							if(!isValid)
								break;
						}// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}
function validateStockCode(form) 
		            {
		                var isValid = true;
		                var focusField = null;
		                var i = 0;
		                var fields = new Array();
		                oStockCode = new stockCode();

						// Start: Loop
		                for (x in oStockCode) 
		                {
		                    var field = form[oStockCode[x][0]];
		                    
		                    if(field == null)
		                		continue;
		                    
		                    if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
					
								var value = '';
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else 
								{
									value = field.value;
								}
					
								if (value.length > 0) 
								{
									if (isAllDigits(value, false) && !isNaN(value)) 
									{
										var iValue = parseInt(value, 10);
				                        if (!(iValue > MIN_VALUE && iValue <= MAX_STOCKCODE)) 
				                        {
				                            isValid = false;
				                        }
				                    }
				                    else
				                    	isValid = false;
								}
								
								if(!isValid)
								{
									isValid = false;
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oStockCode[x][1];
								}
							}
							
							if(!isValid)
								break;
		                }
						// End: Loop

		                if (fields.length > 0) 
		                {
		                    focusField.value="";
		                    focusField.focus();
		                    alert(fields.join('\n'));
		                }
		                return isValid;
		            }
function validateMaxLength(form)
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oMaxLength = new maxlength();
						
						// Start: Loop
						for (x in oMaxLength)
						{
							var field = form[oMaxLength[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea'|| field.type == 'password')
							{
								var iMax = parseInt(oMaxLength[x][2]("maxlength"));
								if (field.value.length > iMax)
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oMaxLength[x][1];
									isValid = false;
								}
							}
							
							if(!isValid)
								break;
						}
						// End: Loop
						
						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}
function clearDependency(form, oFields, x)
					{
						var depFields = oFields[x][2]("depends");
						var depValues = oFields[x][2]("dependValues");

						var bPropValues = false;

						if(depValues == undefined)
						{
							bPropValues = true;
							depValues = oFields[x][2]("dependProbValues");
						}

						if(depFields != null && depFields != '')
						{
							var secondFields = depFields.split(",");

							var secondFieldValues = depValues.split(",");

							var secondField;
							var secondFieldValue;

							for	(var h=0;h<secondFields.length;h++)
							{
								secondField=form[trim(secondFields[h])];

								if(secondField == undefined)
									continue;

								if(!bPropValues)
								{
									secondFieldValue=trim(secondFieldValues[h]);
								}
								else
								{
									var secField = form[trim(secondFieldValues[h])];
									if (secField.type == "select-one")
									{
										var selIndex = secField.selectedIndex;
										secondFieldValue = secField.options[selIndex].value;
									}
									else
										secondFieldValue = secField.value;
								}

								if(secondFieldValue=="NOTBLANK")
								{ 
									if(Trim(secondField.value)=="")
										return false;
									else
										secondFieldValue = secondField.value;
								}

								var value;
								var secondValue;
								// get field's value

								if(secondField == null)
									return false;
								else if (secondField.type == "select-one")
								{
									var si = secondField.selectedIndex;
									secondValue = secondField.options[si].value;
									if(secondValue != secondFieldValue)
										return false;
								}

								else if ((secondField.length > 0) && secondField[0].type == 'radio')
								{
									for(var k = 0; k < secondField.length; k++) 
									{
										if(secondField[k].checked) 
										{
											secondValue = secondField[k].value;
										}
									}
									if(secondValue != secondFieldValue)
										return false;
								}
								else if (secondField.type == 'checkbox')
								{
									if(secondFieldValue == 'true')
									{
										if(!secondField.checked)
											return false;
									}
									else
									{
										if(secondField.checked)
										{
											return false;
										}
									}
								}
								else 
								{
									if(secondFieldValue != secondField.value )
									{
										return false;
									}
								}
							}		
						}
						return true;
					}

					function validateRequired(form) 
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oRequired = new required();
						// Start: Loop
						for (x in oRequired) 
						{

							if(!clearDependency(form, oRequired, x))
								continue;

							var field = form[oRequired[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'file' || field.type == 'select-one' || field.type == 'radio' || field.type == 'password' || 
								field.type == 'checkbox' || (field.length > 0 && field[0].type == 'radio')) 
							{

								var value = '';
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else if (field.type == 'checkbox') 
								{
									if(!field.checked)
										value = '';
									else
										value='checked';
								} 
								else 
								{
									value = field.value;
								}


								if (field.type == 'radio') 
								{
									if (!field.checked) 
									{
										focusField = field;
										fields[i++] = oRequired[x][1];
										isValid=false;
									}
								}
								else if ((field.length > 0) && (field[0].type == 'radio')) 
								{
									isChecked=-1;
									for (loop=0;loop < field.length;loop++) 
									{
										if (field[loop].checked) 
										{
											isChecked=loop;
											break; // only one needs to be checked
										}
									}
									if (isChecked < 0) 
									{
										if (i == 0) 
										{
											focusField = field[0];
										}
										fields[i++] = oRequired[x][1];
										isValid=false;
									}
								}
								else if (trim(value).length == 0) 
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oRequired[x][1];
									isValid = false;
								} 
							}

							if(!isValid)
								break;
						}
						// End: Loop
						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}

					// Trim whitespace from left and right sides of s.
					function trim(s) 
					{
						return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
					}
function validateInteger(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oInteger = new IntegerValidations();

						// Start: Loop
						for (x in oInteger)
						{
							if(!clearDependency(form, oInteger, x))
								continue;

							var field = form[oInteger[x][0]];
							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio')
							{
								var value = '';
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									if (si >= 0)
									{
										value = field.options[si].value;
									}
								}
								else
								{
									value = field.value;
								}

								if (value.length > 0)
								{
									if (!isAllDigits(value, false))
									{
										bValid = false;
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oInteger[x][1];
									}
									else
									{
										var iValue = parseInt(value, 10);
										if (isNaN(iValue) || !(iValue >= -2147483648 && iValue <= 2147483647))
										{
											if (i == 0)
											{
												focusField = field;
											}
											fields[i++] = oInteger[x][1];
											bValid = false;
										}
									}
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function compareDates(date1,date2)
					{
						var tmp = date1.split("/");
		
						date1 = tmp[1]+"/"+tmp[0]+"/"+tmp[2];
						
						if(date2 != undefined)
						{
							tmp = date2.split("/");
							date2 = tmp[1]+"/"+tmp[0]+"/"+tmp[2];
						}
						else
						{
							date2 = getTodaysDate();
						}

						var result = DateDiff(date1, date2);

						if (result > 0)
						{
							return 1;
						}
						else if (result < 0)
						{
							return -1;
						}
						else if (result == 0)
						{
							return 0;
						}
						else
						{
							return -2;
						}
					}
					
					function compareTime(time1,time2)
					{
						var tmp1 = time1.split(":");
						var tmp2 = time2.split(":")
		
						xDate1 = new Date();
						xDate2 = new Date();
						
						if(tmp1.length==1)
							xDate1.setHours(tmp1[0]);
						else if(tmp1.length==2)
							xDate1.setHours(tmp1[0], tmp1[1]);
						else 
							xDate1.setHours(tmp1[0], tmp1[1], tmp1[2]);
							
						if(tmp2.length==1)
							xDate2.setHours(tmp2[0]);
						else if(tmp2.length==2)
							xDate2.setHours(tmp2[0], tmp2[1]);
						else 
							xDate2.setHours(tmp2[0], tmp2[1], tmp2[2]);
					
						if (xDate2.valueOf() > xDate1.valueOf())
						{
							return 1;
						}
						else if (xDate2.valueOf() < xDate1.valueOf())
						{
							return -1;
						}
						else if (xDate2.valueOf() == xDate1.valueOf())
						{
							return 0;
						}
						else
						{
							return -2;
						}
					}
				
					function compareString(value,secvalue)
					{
						if (secvalue > value)
						{
							return 1;
						}
						else if (secvalue < value)
						{
							return -1;
						}
						else if (secvalue == value)
						{
							return 0;
						}
						else
						{
							return -2;
						}
					}
					
					function compareNumber(value, secvalue)
					{
						var iValue = parseFloat(value);
						var iSecValue = parseFloat(secvalue);
						if (iSecValue > iValue)
						{
							return 1;
						}
						else if (iSecValue < iValue)
						{
							return -1;
						}
						else if (iSecValue == iValue)
						{
							return 0;
						}
						else
						{
							return -2;
						}
					}
					
					function isInValid(operator, result)
					{
						var inValid = false;
						if(operator=='gt')
							inValid = (result != 1);
						else if(operator=='gteq')
							inValid = (result == -1);
						else if(operator=='lt')
							inValid = (result != -1);
						else if(operator=='lteq')
							inValid = (result == 1);
						else if(operator=='eq')
							inValid = (result != 0);
						else if(operator=='noteq')
							inValid = (result == 0);
						return inValid;
					}
					
		
			        function validateCompareFields(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oTwoFields = new comparefields();
						
						// Start: Loop
						for (x in oTwoFields) 
						{
		
							if(!clearDependency(form, oTwoFields, x))
								continue;
		
							var field = form[oTwoFields[x][0]];
							if(field == null)
		                		continue;
		
		                	var secondPropName = oTwoFields[x][2]("secondProperty");
		                	var secondPropertyValue = oTwoFields[x][2]("secondPropertyValue");
		
		                	var secondField;
		                	
		                	if(secondPropName != undefined)
								secondField = form[secondPropName];
								
							var type = oTwoFields[x][2]("type");
							var operator = oTwoFields[x][2]("operator");
							if (field.type == 'text' ||
								field.type == 'textarea' ||
								field.type == 'select-one' ||
								field.type == 'radio' ||
								field.type == 'password') 
							{
								
								var value;
								var result;
								var inValid = false;
								
								var secondValue = undefined;
								
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									value = field.options[si].value;
									if(secondPropName != undefined)
									{
										si = secondField.selectedIndex;
										secondValue = secondField.options[si].value;
									}
								} 
								else 
								{
									value = field.value;
									if(secondPropName != undefined)
										secondValue = secondField.value;
								}
								
								if(type=='date')
								{
									if(secondPropName != undefined || secondPropertyValue==undefined)
									{
										result = compareDates(value,secondValue);
										inValid = isInValid(operator, result);
									}
									if(!inValid && (secondPropertyValue != undefined))
									{
										result = compareDates(value,secondPropertyValue);
										inValid = isInValid(operator, result);
									}
								}
								else if(type=='time')
								{
									if(secondPropName != undefined)
									{
										result = compareTime(value,secondValue);
										inValid = isInValid(operator, result);
									}
									if(!inValid && (secondPropertyValue != undefined))
									{
										result = compareTime(value,secondPropertyValue);
										inValid = isInValid(operator, result);
									}
								}
								else if(type=='string')
								{
									if(secondPropName != undefined)
									{
										result = compareString(value,secondValue);
										inValid = isInValid(operator, result);
									}
									if(!inValid && (secondPropertyValue != undefined))
									{
										result = compareString(value,secondPropertyValue);
										inValid = isInValid(operator, result);
									}
								}
								else if(type=='number')
								{
									if((secondPropName != undefined) && chkNumeric(value) && chkNumeric(secondValue))
									{
										result = compareNumber(value, secondValue);
										inValid = isInValid(operator, result);
									}
									if(!inValid && (secondPropertyValue != undefined) && chkNumeric(value) && chkNumeric(secondPropertyValue))
									{
										result = compareNumber(value,secondPropertyValue);
										inValid = isInValid(operator, result);
									}
								}
	
								if (inValid) 
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oTwoFields[x][1];
									bValid = false;
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop
					
						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateMoney(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oTwoFields = new money();
						
						// Start: Loop
						for (x in oTwoFields) 
						{
		
							if(!clearDependency(form, oTwoFields, x))
								continue;
		
							var field = form[oTwoFields[x][0]];
							if(field == null)
		                		continue;
							
							var fieldValue = field.value;
							var format = oTwoFields[x][2]("format");
		
							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio' ||
								field.type == 'password') 
							{
								var tmp = format.split(",");
								
								var decimalCount = 2;
								var numCount = 9;
								
								var idxDot = fieldValue.indexOf(".") ;
								
								var numbers;
								var decimals;
								
								if(idxDot > -1)
								{
									numbers = fieldValue.substring(0, idxDot);
									decimals = fieldValue.substr(idxDot+1);
								}
								else
								{
									numbers = fieldValue;
								}
								
								if(numbers.length > numCount)
									bValid = false;
								else if (decimals != undefined && decimals.length > decimalCount)
									bValid = false;
								else if(isNaN(numbers))
									bValid = false;
								else if(decimals != undefined && isNaN(decimals))
									bValid = false;
								else
									bValid = true;
								if(!bValid)
								{
									fields[i++] = oTwoFields[x][1];
									focusField = field;
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop
					
						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateRange(form)
            		{
                		return validateIntRange(form);
            		}
function validateIntRange(form)
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oRange = new intRange();

						// Start: Loop
						for (x in oRange)
						{
							var field = form[oRange[x][0]];

							if(field == null)
								continue;

							if ((field.type == 'text' || field.type == 'textarea') && (field.value.length > 0))
							{
								var iMin = parseInt(oRange[x][2]("min"));
								var iMax = parseInt(oRange[x][2]("max"));
								var iValue = parseInt(field.value, 10);
								if (!(iValue >= iMin && iValue <= iMax))
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oRange[x][1];
									isValid = false;
								}
							}
							if(!isValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}
function validateListEntry(form) 
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var ctr=0
						var fields = new Array();
						var value = '';
					
						oList = new listValidate();

						// Start: Loop
						for (x in oList) 
						{
							var isValid = false;
							var field = form[oList[x][0]];
							if(field == null)
								continue;
					
							value = field.value;
		
							var list = oList[x][2]("valueList");
					
							if ((field.type == 'text' || field.type == 'radio' || field.type == 'select-one' || field.type == 'textarea') &&
								(trim(value).length > 0) && (trim(list).length > 0)) 
							{
					
					
								var values = list.split(",");
								listSize = values.length;
								// Start: Inner Loop				
								for(ctr=0; ctr<listSize; ctr++)
								{
									isValid = trim(values[ctr]) == trim(value);
									if(isValid)
									{
										break;
									}
								}
								// End: Inner Loop
					
								if(!isValid)
								{
									focusField = field;
									fields[i++] = oList[x][1];
									bValid = false;
								}
							}
							else if ((field.length > 0) && (field[0].type == 'radio' || field[0].type == 'checkbox')) 
							{
					
								isChecked=-1;

								// Start: Inner Loop
								for (loop=0;loop < field.length;loop++) 
								{
									if (field[loop].checked) 
									{
										isChecked=loop;
										value = field[isChecked].value;
										break; // only one needs to be checked
									}
								}
								// End: Inner Loop

								var values = list.split(",");
								listSize = values.length;
								
								// Start: Inner Loop
								for(ctr=0; ctr<listSize; ctr++)
								{
									isValid = trim(values[ctr]) == trim(value);
									if(isValid)
									{
										break;
									}
								}	
								// End: Inner Loop

								if(!isValid)
								{
									focusField = field[isChecked];
									fields[i++] = oList[x][1];
									bValid = false;
								}  
							}
							
							if(!bValid)
								break;
						}
						// End: Loop
					
						if (fields.length > 0) 
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateAmount(form) 
		            {
		            	var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oAmount = new amount();

						// Start: Loop
						for (x in oAmount) 
						{
							if(!clearDependency(form, oAmount, x))
								continue;
								
							var field = form[oAmount[x][0]];
							if(field == null)
								continue;
							
							var nMax = oAmount[x][2]("MAX");
							if(nMax == undefined)
								nMax = MAX_AMOUNT;
								
							var nMin = oAmount[x][2]("MIN");
							if(nMin == undefined)
								nMin = MIN_VALUE;
							
							if (field.type == 'text' ||	field.type == 'textarea' ||	field.type == 'select-one' ||	field.type == 'radio') 
							{
								var value = '';
								// get field's value
								if (field.type == "select-one") 
								{
									var si = field.selectedIndex;
									if (si >= 0) 
									{
										value = field.options[si].value;
									}
								} 
								else
								{
									value = field.value;
								}
								
								if (value.length > 0) 
								{
									var iValue = parseFloat(value);
									if (isNaN(iValue)) 
										bValid = false;
									else if (iValue > MAX_AMOUNT) 
											bValid = false;
		
								}
								
								if(!bValid)
								{
									if (i == 0) 
									{
										focusField = field;
									}
									fields[i++] = oAmount[x][1];
								}
							}
							
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
		                
		            }
function validateShort(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oShort = new ShortValidations();

						// Start: Loop
						for (x in oShort)
						{
							var field = form[oShort[x][0]];

							if(field == null)
								continue;

							if (field.type == 'text' || field.type == 'textarea' || field.type == 'select-one' || field.type == 'radio')
							{
								var value = '';
								// get field's value
								if (field.type == "select-one")
								{
									var si = field.selectedIndex;
									if (si >= 0)
									{
										value = field.options[si].value;
									}
								}
								else
								{
									value = field.value;
								}

								if (value.length > 0)
								{
									if (!isAllDigits(value, false))
									{
										bValid = false;
										if (i == 0)
										{
											focusField = field;
										}
										fields[i++] = oShort[x][1];
									}
									else
									{
										var iValue = parseInt(value, 10);
										if (isNaN(iValue) || !(iValue >= -32768 && iValue <= 32767))
										{
											if (i == 0)
											{
												focusField = field;
											}
											fields[i++] = oShort[x][1];
											bValid = false;
										}
									}
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}
function validateEmail(form)
					{
						var bValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oEmail = new email();

						// Start: Loop
						for (x in oEmail)
						{
							if(!clearDependency(form, oEmail, x))
								continue;

							if(form[oEmail[x][0]] == null)
								continue;

							if ((form[oEmail[x][0]].type == 'text' || form[oEmail[x][0]].type == 'textarea') && (form[oEmail[x][0]].value.length > 0))
							{
								if (!checkEmail(form[oEmail[x][0]].value))
								{
									if (i == 0)
									{
										focusField = form[oEmail[x][0]];
									}
									fields[i++] = oEmail[x][1];
									bValid = false;
								}
							}
							if(!bValid)
								break;
						}
						// End: Loop

						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return bValid;
					}

					/**
					* Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
					* http://javascript.internet.com
					*/
					function checkEmail(emailStr)
					{
						if (emailStr.length == 0)
						{
							return true;
						}
						var emailPat=/^(.+)@(.+)$/;
						var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
						var validChars="\[^\\s" + specialChars + "\]";
						var quotedUser="(\"[^\"]*\")";
						var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
						var atom=validChars + '+';
						var word="(" + atom + "|" + quotedUser + ")";
						var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
						var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
						var matchArray=emailStr.match(emailPat);
						if (matchArray == null)
						{
							return false;
						}
						var user=matchArray[1];
						var domain=matchArray[2];
						if (user.match(userPat) == null)
						{
							return false;
						}
						var IPArray = domain.match(ipDomainPat);
						if (IPArray != null)
						{
							for (var i = 1; i <= 4; i++)
							{
								if (IPArray[i] > 255)
								{
									return false;
								}
							}
							return true;
						}

						var domainArray=domain.match(domainPat);
						if (domainArray == null)
						{
							return false;
						}
						var atomPat=new RegExp(atom,"g");
						var domArr=domain.match(atomPat);
						var len=domArr.length;
						if ((domArr[domArr.length-1].length < 2) || (domArr[domArr.length-1].length > 3))
						{
							return false;
						}
						if (len < 2)
						{
							return false;
						}
						return true;
					}
function validateMask(form)
					{
						var isValid = true;
						var focusField = null;
						var i = 0;
						var fields = new Array();
						oMasked = new mask();
						
						// Start: Loop
						for (x in oMasked)
						{
							if(!clearDependency(form, oMasked, x))
								continue;
								
							var field = form[oMasked[x][0]];

							if(field == null)
								continue;

							if ((field.type == 'text' || field.type == 'textarea'|| field.type == 'password') && (field.value.length > 0))
							{
								if (!matchPattern(field.value, oMasked[x][2]("mask")))
								{
									if (i == 0)
									{
										focusField = field;
									}
									fields[i++] = oMasked[x][1];
									isValid = false;
								}
							}
							
							if(!isValid)
								break;
						}
						// End: Loop
						
						if (fields.length > 0)
						{
							focusField.focus();
							alert(fields.join('\n'));
						}
						return isValid;
					}

					function matchPattern(value, mask)
					{
						return mask.exec(value);
					}