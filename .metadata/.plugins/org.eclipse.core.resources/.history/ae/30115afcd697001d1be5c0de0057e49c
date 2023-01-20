import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//2020-10-09
import junit.framework.TestCase;

public class RecursiveSearcherTest extends TestCase {
	
	final int LOG_BASE_2_OF_FILE_SIZE = (int) (Math.log(10000) / Math.log(2)) + 1;

	IntegerArray arrayToSearch =  new IntegerArray(buildArrayToSearch());
	RecursiveSearcher searcher = new RecursiveSearcher();

	public void testBinarySearchNormal() {
		//'normal' searches
		IntegerArray.reset();
		assertEquals(47, searcher.doBinarySearch(arrayToSearch, 42));
		IntegerArray.reset();
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE);			
	}

	public void testBinarySearchAtBounds() {		
		//search for element at lower bound
		IntegerArray.reset();
		assertEquals(0, searcher.doBinarySearch(arrayToSearch, 2));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE + 2);			
		//search for element at upper bound
		IntegerArray.reset();
		assertEquals(999, searcher.doBinarySearch(arrayToSearch, 996));						
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE + 2);			
	}

	public void testBinarySearchForNonExistantItem() {
		//search for non-existent element
		IntegerArray.reset();
		assertEquals(-1, searcher.doBinarySearch(arrayToSearch, 506));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE);			
		//search for non-existent element at lower bound
		IntegerArray.reset();
		assertEquals(-1, searcher.doBinarySearch(arrayToSearch, 1));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE);			
		//search for non-existent element at upper bound
		IntegerArray.reset();
		assertEquals(-1, searcher.doBinarySearch(arrayToSearch, 1000));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE);			
	}

	public void testBinarySearchForDuplicatedItem() {
		//search for element that is duplicated; should return the 1st occurrence
		//two occurences of an element...
		IntegerArray.reset();		
		assertEquals(812, searcher.doBinarySearch(arrayToSearch, 815));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE + 2);
		
		//six occurences of an element... should return the 1st occurrence
		IntegerArray.reset();
		assertEquals(149, searcher.doBinarySearch(arrayToSearch, 150));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE + 4);			
		
		//ten occurences of an element... should return the 1st occurrence
		IntegerArray.reset();
		assertEquals(980, searcher.doBinarySearch(arrayToSearch, 982));
		assertEquals(true, arrayToSearch.getReads() <= LOG_BASE_2_OF_FILE_SIZE + 10);			
	}


	private int[] buildArrayToSearch() {

		return new int[] {
				2,		3,		3,		4,		4,		6,		6,		7,		7,		9,		// index 00000  to 00009
				10,		10,		10,		12,		12,		16,		16,		18,		18,		18,		// index 00010  to 00019
				19,		19,		20,		20,		20,		20,		21,		21,		22,		24,		// index 00020  to 00029
				25,		26,		26,		26,		27,		27,		32,		33,		33,		34,		// index 00030  to 00039
				35,		36,		36,		38,		38,		39,		39,		42,		44,		44,		// index 00040  to 00049
				45,		46,		47,		47,		48,		49,		49,		49,		49,		50,		// index 00050  to 00059
				52,		53,		54,		54,		55,		57,		58,		58,		59,		61,		// index 00060  to 00069
				61,		62,		62,		62,		63,		63,		63,		65,		65,		66,		// index 00070  to 00079
				68,		69,		73,		75,		75,		76,		76,		77,		77,		77,		// index 00080  to 00089
				78,		81,		84,		84,		85,		85,		86,		86,		88,		92,		// index 00090  to 00099
				95,		95,		97,		97,		99,		100,	101,	101,	102,	102,	// index 00100  to 00109
				103,	104,	105,	106,	107,	107,	108,	108,	110,	110,	// index 00110  to 00119
				111,	113,	115,	116,	117,	117,	118,	119,	119,	121,	// index 00120  to 00129
				122,	122,	124,	129,	129,	130,	131,	134,	136,	137,	// index 00130  to 00139
				139,	141,	142,	142,	144,	145,	146,	147,	147,	150,	// index 00140  to 00149
				150,	150,	150,	150,	150,	151,	152,	155,	157,	157,	// index 00150  to 00159
				157,	158,	158,	159,	160,	161,	164,	164,	165,	167,	// index 00160  to 00169
				171,	172,	173,	174,	174,	174,	175,	177,	177,	178,	// index 00170  to 00179
				178,	178,	179,	179,	180,	182,	183,	183,	186,	186,	// index 00180  to 00189
				187,	192,	193,	196,	198,	199,	199,	200,	200,	201,	// index 00190  to 00199
				201,	202,	203,	204,	205,	210,	212,	212,	213,	215,	// index 00200  to 00209
				217,	219,	219,	221,	221,	221,	223,	223,	223,	224,	// index 00210  to 00219
				225,	225,	225,	227,	228,	229,	230,	230,	231,	231,	// index 00220  to 00229
				232,	232,	233,	234,	236,	236,	236,	237,	238,	238,	// index 00230  to 00239
				238,	239,	240,	240,	241,	243,	244,	245,	245,	246,	// index 00240  to 00249
				247,	249,	251,	253,	253,	254,	254,	255,	256,	256,	// index 00250  to 00259
				257,	258,	261,	262,	262,	267,	268,	270,	271,	272,	// index 00260  to 00269
				272,	273,	273,	273,	275,	276,	278,	278,	278,	278,	// index 00270  to 00279
				279,	281,	282,	282,	282,	284,	286,	286,	287,	288,	// index 00280  to 00289
				288,	289,	289,	289,	290,	291,	291,	293,	293,	294,	// index 00290  to 00299
				294,	296,	301,	302,	302,	302,	302,	303,	304,	307,	// index 00300  to 00309
				307,	307,	308,	309,	310,	311,	316,	317,	318,	318,	// index 00310  to 00319
				318,	319,	321,	322,	323,	323,	324,	324,	324,	325,	// index 00320  to 00329
				325,	325,	328,	329,	331,	331,	331,	331,	332,	332,	// index 00330  to 00339
				335,	339,	343,	344,	344,	344,	345,	348,	349,	355,	// index 00340  to 00349
				357,	357,	357,	357,	357,	359,	359,	360,	361,	361,	// index 00350  to 00359
				361,	362,	365,	366,	367,	368,	368,	373,	376,	376,	// index 00360  to 00369
				378,	378,	379,	379,	380,	381,	381,	381,	383,	383,	// index 00370  to 00379
				384,	384,	385,	385,	386,	387,	387,	391,	393,	396,	// index 00380  to 00389
				399,	399,	399,	400,	402,	402,	402,	403,	403,	405,	// index 00390  to 00399
				405,	405,	405,	406,	407,	411,	411,	411,	412,	412,	// index 00400  to 00409
				413,	414,	415,	416,	417,	418,	418,	421,	422,	424,	// index 00410  to 00419
				425,	425,	425,	428,	429,	429,	434,	434,	434,	435,	// index 00420  to 00429
				436,	437,	437,	437,	438,	438,	439,	439,	440,	441,	// index 00430  to 00439
				441,	441,	443,	444,	444,	445,	446,	447,	447,	448,	// index 00440  to 00449
				450,	450,	452,	454,	455,	457,	458,	459,	460,	460,	// index 00450  to 00459
				461,	462,	462,	463,	465,	466,	466,	467,	469,	470,	// index 00460  to 00469
				470,	470,	471,	473,	474,	475,	475,	475,	479,	480,	// index 00470  to 00479
				480,	481,	481,	481,	482,	482,	482,	484,	485,	485,	// index 00480  to 00489
				487,	490,	490,	490,	491,	495,	496,	498,	498,	499,	// index 00490  to 00499
				499,	499,	499,	500,	500,	501,	501,	502,	503,	504,	// index 00500  to 00509
				504,	504,	505,	505,	508,	510,	512,	513,	513,	513,	// index 00510  to 00519
				514,	515,	517,	518,	518,	519,	519,	521,	523,	523,	// index 00520  to 00529
				524,	524,	525,	525,	525,	525,	527,	528,	529,	531,	// index 00530  to 00539
				531,	531,	532,	532,	533,	533,	534,	538,	539,	539,	// index 00540  to 00549
				539,	540,	540,	540,	541,	541,	542,	542,	545,	545,	// index 00550  to 00559
				548,	548,	550,	550,	550,	551,	552,	553,	555,	557,	// index 00560  to 00569
				559,	559,	562,	563,	564,	565,	566,	567,	567,	568,	// index 00570  to 00579
				570,	571,	572,	572,	574,	574,	575,	576,	578,	580,	// index 00580  to 00589
				581,	583,	584,	585,	586,	587,	587,	588,	589,	590,	// index 00590  to 00599
				591,	592,	592,	592,	593,	594,	595,	596,	601,	601,	// index 00600  to 00609
				604,	607,	609,	609,	610,	610,	611,	611,	613,	616,	// index 00610  to 00619
				616,	619,	622,	622,	623,	623,	624,	627,	628,	628,	// index 00620  to 00629
				631,	631,	634,	639,	639,	639,	639,	640,	642,	642,	// index 00630  to 00639
				645,	645,	646,	648,	648,	651,	651,	652,	655,	655,	// index 00640  to 00649
				658,	658,	659,	659,	659,	661,	662,	663,	664,	664,	// index 00650  to 00659
				665,	666,	666,	667,	668,	669,	669,	671,	672,	675,	// index 00660  to 00669
				677,	679,	680,	680,	682,	684,	684,	686,	686,	686,	// index 00670  to 00679
				687,	687,	689,	692,	694,	695,	696,	696,	700,	700,	// index 00680  to 00689
				703,	703,	704,	704,	705,	705,	705,	705,	706,	707,	// index 00690  to 00699
				710,	713,	713,	713,	715,	716,	716,	717,	717,	719,	// index 00700  to 00709
				720,	720,	720,	721,	722,	722,	723,	723,	723,	724,	// index 00710  to 00719
				724,	727,	727,	729,	729,	730,	731,	731,	739,	741,	// index 00720  to 00729
				742,	742,	743,	743,	744,	745,	745,	745,	745,	748,	// index 00730  to 00739
				749,	749,	751,	753,	753,	754,	754,	754,	756,	757,	// index 00740  to 00749
				758,	759,	759,	760,	761,	762,	766,	766,	766,	766,	// index 00750  to 00759
				767,	767,	767,	769,	770,	771,	773,	773,	773,	774,	// index 00760  to 00769
				774,	774,	775,	776,	776,	776,	778,	779,	780,	783,	// index 00770  to 00779
				783,	785,	786,	788,	789,	789,	791,	792,	792,	793,	// index 00780  to 00789
				794,	794,	795,	796,	799,	800,	800,	800,	801,	801,	// index 00790  to 00799
				801,	802,	803,	803,	804,	806,	807,	807,	807,	809,	// index 00800  to 00809
				810,	811,	815,	815,	816,	816,	816,	817,	817,	819,	// index 00810  to 00819
				820,	821,	823,	825,	826,	826,	827,	827,	830,	833,	// index 00820  to 00829
				834,	834,	834,	835,	836,	836,	837,	837,	838,	838,	// index 00830  to 00839
				838,	840,	841,	841,	844,	844,	845,	846,	846,	847,	// index 00840  to 00849
				848,	849,	849,	851,	853,	854,	854,	856,	856,	856,	// index 00850  to 00859
				858,	858,	860,	860,	861,	861,	861,	862,	864,	864,	// index 00860  to 00869
				864,	865,	865,	866,	866,	866,	866,	873,	875,	875,	// index 00870  to 00879
				876,	876,	878,	882,	885,	887,	887,	887,	888,	891,	// index 00880  to 00889
				891,	892,	894,	895,	895,	895,	896,	896,	896,	897,	// index 00890  to 00899
				897,	898,	899,	902,	904,	904,	908,	908,	908,	909,	// index 00900  to 00909
				910,	911,	912,	912,	912,	912,	913,	913,	914,	915,	// index 00910  to 00919
				916,	916,	917,	918,	918,	920,	921,	922,	923,	926,	// index 00920  to 00929
				926,	928,	930,	930,	932,	932,	934,	935,	935,	935,	// index 00930  to 00939
				936,	936,	936,	936,	937,	938,	940,	942,	942,	944,	// index 00940  to 00949
				945,	947,	948,	951,	954,	955,	957,	957,	957,	962,	// index 00950  to 00959
				966,	966,	967,	967,	968,	969,	971,	972,	972,	973,	// index 00960  to 00969
				974,	975,	975,	975,	976,	977,	978,	978,	981,	981,	// index 00970  to 00979
				982,	982,	982,	982,	982,	982,	982,	982,	982,	982,	// index 00980  to 00989
				990,	990,	990,	991,	992,	992,	993,	994,	995,	996		// index 00990  to 00999
		};

	}
	
}
