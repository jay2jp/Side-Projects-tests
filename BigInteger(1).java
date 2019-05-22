package math;

public class BigInteger {
	boolean turnneg = false;
	boolean negative;
	int numDigits;
	DigitNode front;

	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}

	public static BigInteger parse(String integer) throws IllegalArgumentException {
// done on september 20
		String cutafter;
		String thenumbers = integer;
		int zerov = 0;
		thenumbers = thenumbers.trim();
		BigInteger parser = new BigInteger();
		if (thenumbers.charAt(0) == '+') {
			thenumbers = thenumbers.substring(1, thenumbers.length());
		}
		if (thenumbers.charAt(0) == '-') {
			thenumbers = thenumbers.substring(1, thenumbers.length());
			parser.negative = true;
		}

		boolean falsetest = false;
		for (int i = 0; i <= (thenumbers.length() - 1); i++) {
			if (thenumbers.charAt(i) != '0') {
				thenumbers = thenumbers.substring(zerov, thenumbers.length());
				falsetest = true;
				break;
			} else {
				zerov++;
			}
		}
		if (falsetest == false) {
			thenumbers = "0";
		}

		if (thenumbers.matches("^[0-9]*$")) {
			cutafter = thenumbers;
		} else {
			throw new IllegalArgumentException();

		}
		boolean virgin = true;
		boolean tempstat = true;
		DigitNode start = new DigitNode(Integer.parseInt(cutafter.substring(0, 1)), null);
		parser.front = start;
		DigitNode temp = new DigitNode(2, null);
		DigitNode temp2 = new DigitNode(2, null);
		if (cutafter.length() >= 2) {
			for (int x = 1; x <= cutafter.length() - 1; x++) {
				if (virgin == false && (tempstat == false)) {
					temp = new DigitNode(Integer.parseInt(cutafter.substring(x, x + 1)), temp2);
					parser.front = temp;
					tempstat = true;
				} else {
					if (virgin == false && (tempstat == true)) {
						temp2 = new DigitNode(Integer.parseInt(cutafter.substring(x, x + 1)), temp);
						parser.front = temp2;
						tempstat = false;
					}
				}
				if (virgin == true) {
					temp = new DigitNode(Integer.parseInt(cutafter.substring(x, x + 1)), start);
					parser.front = temp;
					virgin = false;
				}
			}
		}
		return parser;
	}

	public BigInteger add(BigInteger other) {
		
		//done september
		BigInteger clones = new BigInteger();
		clones = this;
		if (clones.negative == true && other.negative == false) {
			clones = subtractionpremethod(other);
		} else {
			if (other.negative == true && clones.negative == false) {
				clones = subtractionpremethod(other);
			} else {
				if (clones.negative == true && clones.negative == true) {
					clones = additonpremethod(other);
					clones.negative = true;
				} else {
					if (clones.negative != true && clones.negative != true) {
						clones = additonpremethod(other);
					}
				}
			}
		}
		return clones;
	}

	public BigInteger multiply(BigInteger other) {
		// done on september 25
		boolean turnnegativity = false;
		BigInteger patchadd = new BigInteger();
		DigitNode firstnode = new DigitNode(0, null);
		patchadd.front = firstnode;

		if (other.negative == true && this.negative == true) {
			other.negative = false;
			this.negative = false;
		} else {
			if (other.negative == true || this.negative == true) {
				other.negative = false;
				this.negative = false;
				turnnegativity = true;
			}
		}

		BigInteger bottomsup = other;
		BigInteger topdown = this;
		int otherassigned;
		int cloneassigned;
		if (other.toString().length() >= 10) {
			if (other.negative != true) {
				otherassigned = Integer.parseInt(other.toString().substring(0, 8));

			} else {
				otherassigned = Integer.parseInt(other.toString().substring(1, 9));
			}
		} else {
			otherassigned  = Integer.parseInt(other.toString().substring(0, other.toString().length()));
		}
		if (this.toString().length() >= 10) {
			if (this.negative != true) {
				cloneassigned = Integer.parseInt(this.toString().substring(0, 8));
			} else {
				cloneassigned = Integer.parseInt(this.toString().substring(1, 9));
			}
		} else {
			cloneassigned = Integer.parseInt(this.toString().substring(0, this.toString().length()));
		}
		if (otherassigned > cloneassigned) {
			topdown = other;
			bottomsup = this;
		} else {
			bottomsup = other;
			topdown = this;
		}
		String tempstring = topdown.toString();
		int addfactor = 0;
		int sum = 0;
		int bottomnum;
		String totalsum = "";
		String oursum = "";
		BigInteger oursumnode = new BigInteger();
		BigInteger totalsumnode = new BigInteger();
		int row = 0;
		int topnum;
		String result = "";
		while (bottomsup.front != null || topdown.front != null) {
			sum = addfactor;
			if (topdown.front == null && addfactor != 0) {
				oursum = oursum + addfactor;
				addfactor = 0;
			} else {
				if (topdown.front != null && bottomsup.front != null) {
					bottomnum = Integer.parseInt(bottomsup.front.toString());
					topnum = Integer.parseInt(topdown.front.toString());
					sum += bottomnum * topnum;
					topdown.front = topdown.front.next;
					addfactor = sum / 10;
					sum = sum % 10;
					oursum = oursum + sum;
				} else {
					if (bottomsup.front != null) {
						oursum = reversedigits(oursum);
						totalsumnode = BigInteger.parse(oursum);
						patchadd= patchadd.add(totalsumnode);
						totalsumnode = totalsumnode.add(oursumnode);
						sum = 0;
						addfactor = 0;
						bottomsup.front = bottomsup.front.next;
						BigInteger topback = BigInteger.parse(tempstring);
						topdown = topback;
						row++;
						totalsum = result;
						String nextstring = "";
						for (int s = 0; s < row; s++) {
							nextstring = nextstring + "0";
						}
						oursumnode = BigInteger.parse(oursum);
						oursum = nextstring;
					} else {
						if (bottomsup.front == null) {
							break;
						}
					}
				}
			}
		}
		DigitNode zerosss = new DigitNode(0, null);

		oursumnode.front = zerosss;
		totalsumnode.front = zerosss;
		totalsumnode = patchadd;
		if (turnnegativity == true) {
			totalsumnode.negative = true;
		}
		return totalsumnode;
	}
	public String toString() {
		if (front == null) {
			return "0";
		}

		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
			retval = curr.digit + retval;
		}

		if (negative) {
			retval = '-' + retval;
		}

		return retval;
	}

	private static BigInteger reversenodes(BigInteger thething) {
		BigInteger reverse = new BigInteger();
		DigitNode nextone = new DigitNode(1, null);
		while (thething.front != null) {
			nextone = thething.front.next;
			thething.front.next = reverse.front;
			reverse.front = thething.front;
			thething.front = nextone;
		}
		return reverse;
	}

	private BigInteger additonpremethod(BigInteger other) {
		BigInteger clone = new BigInteger();
		int carry = 0;
		clone = this;
		boolean tempstat = true;
		DigitNode temp2t = null;
		DigitNode temp = null;
		BigInteger NodeAdditon = new BigInteger();
		int sum = 0;
		int first = 0;
		while (clone.front != null || other.front != null) {
			first++;
			sum = carry;
			if (clone.front != null) {
				sum = sum + Integer.parseInt(clone.front.toString());
				clone.front = clone.front.next;
			}
			if (other.front != null) {
				sum = sum + Integer.parseInt(other.front.toString());
				other.front = other.front.next;
			}
			carry = sum / 10;
			sum = sum % 10;
			if (first > 1 && (tempstat == false)) {
				temp = new DigitNode(sum, temp2t);
				NodeAdditon.front = temp;
				tempstat = true;
			} else {
				if (first > 1 && (tempstat == true)) {
					temp2t = new DigitNode(sum, temp);
					NodeAdditon.front = temp2t;
					tempstat = false;
				}
			}
			if (first == 1) {
				temp = new DigitNode(sum, null);
				NodeAdditon.front = temp;
			}
		}
		if (carry != 0 && tempstat == false) {
			DigitNode tempnode = new DigitNode(carry, temp2t);
				NodeAdditon.front = tempnode;
		}
		if (carry != 0 && tempstat == true) {
			DigitNode tempnode = new DigitNode(carry, temp);
			NodeAdditon.front = tempnode;
		}
		return reversenodes(NodeAdditon);
	}

	private BigInteger subtractionpremethod(BigInteger other) {
		BigInteger clone = new BigInteger();
		int carry = 0;
		clone = this;
		boolean tempstat = true;
		DigitNode temp2 = null;
		DigitNode temp = null;
		BigInteger addnode = new BigInteger();
		BigInteger tempnode2 = new BigInteger();
		int sum = 0;
		int first = 0;
		int otherasint;
		int cloneasint;
		if (other.negative == false) {
			tempnode2 = other;
			other = clone;
			clone = tempnode2;
		}
		if (other.toString().length() >= 10) {
			if (other.negative == true) {
				otherasint = Integer.parseInt(other.toString().substring(1, 10));
			} else {
				otherasint = Integer.parseInt(other.toString().substring(0, 9));
			}
		} else {
			if (other.negative == true) {
				otherasint = Integer.parseInt(other.toString().substring(1, other.toString().length()));
			} else {
				otherasint = Integer.parseInt(other.toString().substring(0, other.toString().length()));
			}
		}
		if (clone.toString().length() >= 10) {
			if (clone.negative == true) {
				cloneasint = Integer.parseInt(clone.toString().substring(1, 10));
			} else {
				cloneasint = Integer.parseInt(clone.toString().substring(0, 9));
			}

		} else {
			if (clone.negative == true) {
				cloneasint = Integer.parseInt(clone.toString().substring(1, clone.toString().length()));
			} else {
				cloneasint = Integer.parseInt(clone.toString().substring(0, clone.toString().length()));

			}
		}

		if (otherasint > cloneasint) {
			tempnode2 = other;
			other = clone;
			clone = tempnode2;
			turnneg = true;
		} else {
		}
		while (clone.front != null || other.front != null) {
			first++;
			sum = carry;
			if ((other.front == (null)) && (clone.front != null)) {
				if (clone.front.toString().equals("0") && carry != 0) {
					System.out.println("carry is not 0");

					sum += Integer.parseInt(clone.front.toString()) + 10;
					carry = -1;
					clone.front = clone.front.next;
				} else {
					sum += Integer.parseInt(clone.front.toString());
					clone.front = clone.front.next;
					carry = 0;
				}
			} else {
				if (clone.front == null || other.front == null) {
					break;
				}
				if (Integer.parseInt(clone.front.toString()) == Integer.parseInt(other.front.toString())
						&& carry == -1) {
					sum += Integer.parseInt(clone.front.toString()) + 10 - Integer.parseInt(other.front.toString());
					carry = -1;

					clone.front = clone.front.next;
					other.front = other.front.next;
				} else {
					if (Integer.parseInt(clone.front.toString()) >= Integer.parseInt(other.front.toString())) {
						sum += Integer.parseInt(clone.front.toString()) - Integer.parseInt(other.front.toString());
						System.out.println("we are subtracting " + Integer.parseInt(clone.front.toString()) + " - "
								+ Integer.parseInt(other.front.toString()));
						carry = 0;
						clone.front = clone.front.next;
						other.front = other.front.next;
					} else {
						if (clone.front.next != null) {
							sum += Integer.parseInt(clone.front.toString()) + 10
									- Integer.parseInt(other.front.toString());
							System.out.println(
									"we are subtracting/carrying " + (Integer.parseInt(clone.front.toString()) + 10)
											+ " - " + Integer.parseInt(other.front.toString()));
							carry = -1;
							clone.front = clone.front.next;
							other.front = other.front.next;
						} else {
							sum += Integer.parseInt(clone.front.toString()) + 10
									- Integer.parseInt(other.front.toString());
							sum = sum * -1;
							carry = 0;
							clone.front = clone.front.next;
							other.front = other.front.next;
						}
					}
				}
			}
			if (other.front == null && clone.front == null) {
			}
			if (first == 1) {
				temp = new DigitNode(sum, null);
				addnode.front = temp;
			} else {
				if (tempstat == false) {
					temp = new DigitNode(sum, temp2);
					addnode.front = temp;
					tempstat = true;
				} else {
					if (tempstat == true) {
						temp2 = new DigitNode(sum, temp);
						addnode.front = temp2;
						tempstat = false;
					}
				}
			}
		}
		addnode = reversenodes(addnode);
		if (turnneg == true) {
			addnode.negative = true;
		}
		addnode = zeroelim(addnode);
		return addnode;
	}

	private BigInteger zeroelim(BigInteger other) {
		DigitNode zero = new DigitNode(0, null);
		BigInteger zeroresult = new BigInteger();
		zeroresult.front = zero;
		boolean allzero = true;
		char z;
		int zerocount1 = 0;
		boolean neg = false;
		for (int x = 0; x < other.toString().length(); x++) {
			z = other.toString().charAt(x);
			System.out.println(z);
			if (z == '-') {
				//System.out.println("triggered");  ddy
				neg = true;
			}
			if (z != '0' && z != '-') {
				allzero = false;
				break;
			} else {
				zerocount1++;
			}
		}
		if (allzero == true) {
			return zeroresult;
		} else {
			other = reversenodes(other);
			if (neg != false) {
				zerocount1 = zerocount1 - 1;
				neg = false;
			}
			for (int q = 0; q < zerocount1; q++) {
				other.front = other.front.next;
				System.out.println(other.toString());
			}
			other = reversenodes(other);
			if (turnneg == true) {
				other.negative = true;
			}
			return other;
		}
	}

	private static String reversedigits(String input) {
		int beg = 0;
		char[] a = input.toCharArray();

		int end = a.length - 1;
		char temp;
		while (end > beg) {
			temp = a[beg];
			a[beg] = a[end];
			a[end] = temp;
			end--;
			beg++;
		}
		return new String(a);
	}
}
