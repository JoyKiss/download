package com.e12e.main;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.e12e.service.DownloadFile;
import com.e12e.service.GetAttachFile;
import com.e12e.service.GetInfo;
import com.e12e.service.GetList;
import com.e12e.service.GetOtherVideo;

public class test {
	public static void main(String[] args) {
		// String no = "230 236 237 238 239 240 241 242 243 244 245 247 248 249
		// 250 251 252 254 255 256 257 258 259 260 261 262 263 264 265 267 268
		// 269 270 271 272 273 274 276 277 278 282 283 284 285 286 287 288 289
		// 291 292 293 295 296 297 298 299 300 302 303 304 305 308 310 311 312
		// 313 317 325 327 329 330 331 332 333 334 336 337 338 339 340 341 342
		// 343 344 345 346 347 348 349 350 352 353 354 355 356 357 358 359 360
		// 361 362 363 364 365 366 367 368 369 370 371 372 373 374 375 376 377
		// 378 379 380 381 382 383 384 385 386 387 388 389 390 391 392 393 394
		// 395 396 397 398 399 400 401 402 403 404 405 406 407 408 409 410 411
		// 412 413 414 415 416 417 418 419 420 421 422 423 424 425 426 427 428
		// 429 430 431 432 433 434 435 436 437 438 440 441 442 443 444 445 446
		// 447 448 449 450 451 452 453 454 455 456 457 458 459 460 461 462 463
		// 464 465 466 467 468 469 470 471 472 473 474 475 476 477 478 479 480
		// 481 482 483 484 485 486 487 488 489 490 491 492 493 494 496 497 498
		// 499 500 501 502 504 505 506 507 508 509 510 511 513 514 515 516 517
		// 518 519 520 521 522 523 524 525 526 527 528 529 530 531 532 533 534
		// 535 536 537 538 539 540 541 542 543 544 545 546 547 548 549 550 551
		// 552 554 555 556 557 558 559 560 561 562 563 564 565 566 567 568 572
		// 574 575 576 578 579 580 581 582 583 584 585 586 587 588 589 590 591
		// 592 593 594 595 596 597 598 599 600 601 603 604 606 607 608 609 610
		// 611 612 613 614 615 616 617 618 619 620 621 622 623 629 630 631 632
		// 633 634 635 636 637 639 640 641 642 643 644 645 646 648 649 650 651
		// 652 653 654 655 656 657 658 659 660 661 662 663 664 665 666 667 668
		// 669 670 671 672 673 674 675 676 677 678 679 680 681 682 683 684 686
		// 687 689 690 691 693 694 696 697 698 699 700 701 702 703 705 706 707
		// 708 709 710 711 712 713 714 715 716 717 718 719 720 721 722 723 724
		// 725 726 727 728 729 731 732 734 735 736 737 738 739 740 741 742 743
		// 744 745 746 747 748 749 750 751 752 753 754 755 756 757 758 759 760
		// 761 762 763 764 765 766 767 768 770 771 773 774 777 778 782 784 785
		// 786 788 789 790 791 792 793 794 795 796 797 798 799 800 801 802 803
		// 805 806 807 808 809 810 811 812 813 814 815 816 818 819 820 821 822
		// 823 824 825 828";
		// String no =
		// "782,784,785,786,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,805,806,807,808,809,810,811,812,813,814,815,816,818,819,820,821,822,823,824,825,828";
		String no = "829,832,833";
		// String no =
		// "5,6,8,9,10,12,14,15,17,18,20,21,22,23,24,26,30,31,32,33,34,35,36,37,39,40,41,42,43,44,46,47,48,50,51,52,53,54,56,57,58,59,60,61,62,65,68,69,71,72,74,75,76,77,80,81,85,90,91,92,93,94,95,96,99,100,101,102,103,104,107,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,170,171,172,173,174,175,176,177,178,179,180,181,182,184,185,186,191,192,193,194,195,196,197,198,199,201,202,203,204,205,206,207,208,211,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,236,237,238,239,240,241,242,243,244,245,247,248,249,250,251,252,254,255,256,257,258,259,260,261,262,263,264,265,267,268,269,270,271,272,273,274,276,277,278,282,283,284,285,286,287,288,289,291,292,293,295,296,297,298,299,300,302,303,304,305,308,310,311,312,313,317,325,327,329,330,331,332,333,334,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,496,497,498,499,500,501,502,504,505,506,507,508,509,510,511,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,572,574,575,576,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,603,604,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,629,630,631,632,633,634,635,636,637,639,640,641,642,643,644,645,646,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,686,687,689,690,691,693,694,696,697,698,699,700,701,702,703,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,731,732,734,735,736,737,738,739,740,741,742,743,744,745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,770,771,773,774,777,778,782,784,785,786,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,805,806,807,808,809,810,811,812,813,814,815,816,818,819,820,821,822,823,824,825,828";
		String[] nos = no.split(",");
		for (String notest : nos) {
			try {
				doMain(Integer.parseInt(notest));
				// rename(Integer.parseInt(notest));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
	}

	public static void rename(int classNo) throws JSONException {
		System.out.print(classNo + "...    ");
		Document doc = null;
		int curruntCount = 0;
		boolean flag = true;
		try {
			doc = Jsoup.connect("http://www.imooc.com/learn/" + classNo).get();
			String title = doc.getElementsByTag("h2").html();
			title = title.replaceAll("[\\\\/:\\*\\?\"<>\\|]", "#");
			String savePath = "./download/" + title + "/";
			File file = new File(savePath);
			if (file.exists()) {
				String changPath = "./download/" + classNo + "_" + title + "/";
				file.renameTo(new File(changPath));
				System.out.print(changPath);
			}
		} catch (IOException e) {
			System.out.println("获取课程信息时网络异常！可以稍后重试~\n");
		}
		System.out.println();

	}

	public static void doMain(int classNo) throws JSONException {

		Document doc = null;
		int curruntCount = 0;
		boolean flag = true;
		try {
			doc = Jsoup.connect("http://www.imooc.com/learn/" + classNo).get();
		} catch (IOException e) {
			System.out.println("获取课程信息时网络异常！可以稍后重试~\n");
		}

		String title = doc.getElementsByTag("h2").html();
		title = title.replaceAll("[\\\\/:\\*\\?\"<>\\|]", "#");
		title = classNo + "_" + title;
		String savePath = "./download/" + title + "/";
		File file = new File(savePath);

		Elements videos = doc.select("ul.video a");
		if ((title.equals("")) && (videos.size() == 0)) {
			System.out.println("抱歉，没有该课程！\n");
		} else {
			int count = 0;
			for (Element video : videos) {
				String[] videoNos = video.attr("href").split("/");

				if (videoNos[1].equals("video")) {
					count++;
				}
			}
			System.out.print("\n要下载的课程标题为【" + title + "】，");
			System.out.println(
					"共 " + videos.size() + " 节课程，其中视频课程有 " + count + " 节\n");

			// int videoDef = GetInput.getInputVideoDef();
			int videoDef = 1;
			System.out.println("\n正在下载，请耐心等待…\n");

			for (Element video : videos) {
				curruntCount++;
				String[] videoNos = video.attr("href").split("/");
				try {
					GetAttachFile.doGetFile(videoNos[2], title);
				} catch (IOException e) {
					System.out.println("下载课程资料附件时出现异常！\n");
				}

				if (flag) {
					file.mkdirs();
					try {
						GetInfo.doGetInfo(classNo, title);

						System.out.println("课程信息 course_info.txt 生成成功！");
					} catch (Exception e2) {
						e2.printStackTrace();
						System.out.println("生成course_info.txt时出现异常！");
					}

					try {
						GetList.doGetList(videos, savePath);
						System.out.println("课程列表 course_list.html 生成成功！");
					} catch (Exception e1) {
						System.out.println("生成course_list.html时出现异常！");
					}
					flag = false;
				}

				if (videoNos[1].equals("video")) {
					String videoName = ((TextNode) video.textNodes().get(1))
							.text()
							.substring(0, ((TextNode) video.textNodes().get(1))
									.text().length() - 8)
							.trim();
					videoName = videoName.replaceAll("[\\\\/:\\*\\?\"<>\\|]",
							"#");
					String videoNo = videoNos[2];
					Document jsonDoc = null;
					try {
						jsonDoc = Jsoup
								.connect(
										"http://www.imooc.com/course/ajaxmediainfo/?mid="
												+ videoNo + "&mode=flash")
								.timeout(1000).get();
					} catch (IOException e) {

						System.out.println("【" + curruntCount + "】" + videoName
								+ "\t网络异常，地址获取失败！");
						continue;
					}

					String jsonData = jsonDoc.text();
					JSONObject jsonObject = new JSONObject(jsonData);
					if ("false".equals(jsonObject.optJSONObject("data")
							.optJSONObject("result").get("mpath").toString())) {

						try {
							String downloadPath = GetOtherVideo
									.getVideoUrl(videoNo);
							DownloadFile.downLoadFromUrl(downloadPath,
									videoName + ".mp4", savePath);
							System.out.println("【" + curruntCount + "】"
									+ videoName + " \t下载成功！");
						} catch (IOException e) {
							System.out.println("【" + curruntCount + "】：\t"
									+ videoName + " \t网络异常，下载失败！");
						}
						continue;
					}
					JSONArray mpath = jsonObject.optJSONObject("data")
							.optJSONObject("result").optJSONArray("mpath");
					String downloadPath = mpath.getString(videoDef).trim();
					try {
						DownloadFile.downLoadFromUrl(downloadPath,
								videoName + ".mp4", savePath);
						System.out.println("【" + curruntCount + "】" + videoName
								+ " \t下载成功！");
					} catch (IOException e) {
						System.out.println("【" + curruntCount + "】：\t"
								+ videoName + " \t网络异常，下载失败！");
					}
				}
			}

			System.out.println("\n【" + title + "】课程的下载任务已完成！！！\n已下载到该程序所在目"
					+ "录download文件夹下。\n慕课网视频批量下载工具 v1.6  By Coande"
					+ "\n----------------------------------------------------------\n");
		}
	}
}
