	package net.homeunix.nac.media;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import net.homeunix.nac.common.DayComparator;
import net.homeunix.nac.common.DayComparator.DIRECTION;
import net.homeunix.nac.common.constant;
import net.homeunix.nac.common.io.FilenamePatternWithLastmodifiedFilter;


//VS4E -- DO NOT REMOVE THIS LINE!
public class Manager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JComboBox jComboBox_fromday;	private JScrollPane jScrollPane_file;
	private JLabel jLabel1;
	private JComboBox jComboBox_move;
	private JTree jTree_file;
	private JButton jButton_move;
	private JButton jButton_accept;
	private JButton jButton_expand;
	private JLabel jLabel2;
	private JComboBox jComboBox_today;
	public Manager() {
		initComponents();
	}

	private void initComponents() {
		setTitle("media manager");
		setLayout(new GroupLayout());
		add(getJComboBox_fromday(), new Constraints(new Leading(62, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(17, 10, 10), new Leading(12, 25, 12, 12)));
		add(getJScrollPane_file(), new Constraints(new Leading(16, 585, 10, 10), new Leading(42, 200, 12, 12)));
		add(getJButton_move(), new Constraints(new Leading(545, 10, 10), new Leading(12, 6, 6)));
		add(getJComboBox_move(), new Constraints(new Leading(427, 117, 6, 6), new Leading(13, 6, 6)));
		add(getJLabel1(), new Constraints(new Leading(384, 40, 10, 10), new Leading(13, 25, 6, 6)));
		add(getJLabel2(), new Constraints(new Leading(141, 6, 6), new Leading(16, 6, 6)));
		add(getJComboBox_today(), new Constraints(new Leading(159, 70, 6, 6), new Leading(11, 6, 6)));
		add(getJButton_accept(), new Constraints(new Leading(241, 6, 6), new Leading(10, 6, 6)));
		add(getJButton_expand(), new Constraints(new Leading(297, 10, 10), new Leading(11, 6, 6)));
		setSize(615, 254);
	}

	private JComboBox getJComboBox_today() {
		if (jComboBox_today == null) {
			jComboBox_today = new JComboBox();
			jComboBox_today.setModel(new DefaultComboBoxModel(new Object[] { "今日", "昨日", "一昨日", "3日前", "4日前", "5日前", "6日前", "1週間前", "2週間前", "1ヶ月前", "2ヶ月前", "3ヶ月前",
					"6ヶ月前", "1年前" }));
			jComboBox_today.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent event) {
					jComboBox_todayItemItemStateChanged(event);
				}
			});
		}
		return jComboBox_today;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("～");
		}
		return jLabel2;
	}

	private JButton getJButton_expand() {
		if (jButton_expand == null) {
			jButton_expand = new JButton();
			jButton_expand.setText("展開");
			jButton_expand.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					jButton_expandActionActionPerformed(event);
				}
			});
		}
		return jButton_expand;
	}

	private JButton getJButton_accept() {
		if (jButton_accept == null) {
			jButton_accept = new JButton();
			jButton_accept.setText("検収");
			jButton_accept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					jButton_acceptActionActionPerformed(event);
				}
			});
		}
		return jButton_accept;
	}

	private JButton getJButton_move() {
		if (jButton_move == null) {
			jButton_move = new JButton();
			jButton_move.setText("移動");
			jButton_move.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					jButton_moveActionActionPerformed(event);
				}
			});
		}
		return jButton_move;
	}

	private JScrollPane getJScrollPane_file() {
		if (jScrollPane_file == null) {
			jScrollPane_file = new JScrollPane();
			jScrollPane_file.setViewportView(getJTree_file());
		}
		return jScrollPane_file;
	}

	private JTree getJTree_file() {
		if (jTree_file == null) {
			jTree_file = new JTree();
			DefaultTreeModel treeModel = null;
			{
				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("JTree");
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("colors");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("blue");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("violet");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("red");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("yellow");
						node1.add(node2);
					}
					node0.add(node1);
				}
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("sports");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("basketball");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("soccer");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("football");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hockey");
						node1.add(node2);
					}
					node0.add(node1);
				}
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("food");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hot dogs");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("pizza");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("ravioli");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("bananas");
						node1.add(node2);
					}
					node0.add(node1);
				}
				treeModel = new DefaultTreeModel(node0);
			}
			jTree_file.setModel(treeModel);
			jTree_file.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent event) {
					jTree_fileMouseMouseClicked(event);
				}
			});
			jTree_file.addTreeSelectionListener(new TreeSelectionListener() {

				public void valueChanged(TreeSelectionEvent event) {
					jTree_fileTreeSelectionValueChanged(event);
				}
			});
			jTree_file.addKeyListener(new KeyAdapter() {

				public void keyPressed(KeyEvent event) {
					jTree_fileKeyKeyPressed(event);
				}
			});
		}
		return jTree_file;
	}

	private JComboBox getJComboBox_move() {
		if (jComboBox_move == null) {
			jComboBox_move = new JComboBox();
			jComboBox_move.setModel(new DefaultComboBoxModel(new Object[] { "libxxx", "media" }));
			jComboBox_move.setDoubleBuffered(false);
			jComboBox_move.setBorder(null);
		}
		return jComboBox_move;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("移動先");
		}
		return jLabel1;
	}

	private JComboBox getJComboBox_fromday() {
		if (jComboBox_fromday == null) {
			jComboBox_fromday = new JComboBox();
			jComboBox_fromday.setModel(new DefaultComboBoxModel(this.ALLDAYS.toArray()));
			jComboBox_fromday.setBorder(null);
			jComboBox_fromday.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent event) {
					jComboBox_fromdayItemItemStateChanged(event);
				}
			});
		}
		return jComboBox_fromday;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("対象日");
		}
		return jLabel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		//Swingコンポーネント起動

		SwingUtilities.invokeLater(new Runnable() {
//			@Override
			public void run() {
				Manager frame = new Manager();
				//使用する期間表記のリストALLDAYSをソートする
				Collections.sort(frame.ALLDAYS, new DayComparator<String>(DIRECTION.DESCEND));

				try {
					//設定ファイル読み込み
					System.out.println("mediamanager.propertiesを以下ディレクトリーから読み込みます"+System.getProperty("user.dir"));
					frame.loadProperty(System.getProperty("user.dir") + frame.fs_delimiter + "mediamanager.properties");

					//ウィンドウ設定
					frame.setDefaultCloseOperation(Manager.EXIT_ON_CLOSE);
					frame.setTitle("Media Manager");
					frame.getContentPane().setPreferredSize(frame.getSize());
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

					//初期化
					frame.initialize(new FilenamePatternWithLastmodifiedFilter(Pattern.compile(frame.getFilenamePattern())));

					//ツリービュー更新
					frame.prepareTree();

					//設定保存
//					frame.saveProperty();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	public final String fs_delimiter = System.getProperty("file.separator");
	public final List<String> ALLDAYS = Arrays.asList(new String[] { "今日", "昨日", "一昨日", "3日前", "4日前", "5日前", "6日前", "1週間前", "2週間前", "1ヶ月前", "2ヶ月前", "3ヶ月前", "6ヶ月前", "1年前" });
	private enum ACCEPT_ACTION { MOVE, DELETE };
	private String filename;
	private Properties property;
	private String directory;
	private String filenamepattern;
	private String accept_action;
	private String master_directory;
	private String accepted_directory;
	private List<String> move_names;
	private Map<String, String> move_paths;
	private FilenamePatternWithLastmodifiedFilter filter;
	private boolean listener = true;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	/**
	 * propertiesファイルから読み込み
	 * @param filename ファイル名
	 */
	private void loadProperty(String filename) throws FileNotFoundException, IOException {
		//propertiesファイルロード
		this.property = new Properties();
		this.filename = filename;
		this.property.load(new FileInputStream(this.filename));

		//property取り出し
		this.directory = this.property.getProperty("directory");
		this.filenamepattern = this.property.getProperty("filenamepattern");
		this.accept_action = this.property.getProperty("accept_action");
		this.master_directory = this.property.getProperty("master_directory");
		this.accepted_directory = this.property.getProperty("accepted_directory");
		List<String> paths = java.util.Arrays.asList(this.property.getProperty("libraries").split(","));

		//librariesをsavepathへ分離保存
		String[] pair;
		this.move_names = new ArrayList<String>();
		this.move_paths = new HashMap<String, String>();
		for (String path : paths) {
			pair = path.split("/");
			this.move_names.add(pair[0]);
			this.move_paths.put(pair[0], pair[1]);
		}
	}

	/**
	 * プロパティを保存する
	 */
//	@SuppressWarnings("deprecation")
	private void saveProperty() throws FileNotFoundException, IOException, NullPointerException {
		if (this.property == null) throw new NullPointerException("property not set. Maybe not call loadProperty() before.");
		if (this.filename == null) throw new NullPointerException("filename not set. Maybe not call loadProperty() before.");

		this.property.clear();
		this.property.setProperty("directory", this.directory);
		this.property.setProperty("filenamepattern", this.filenamepattern);

		StringBuffer libraries = null;
		for (String name : this.move_names) {
			if (libraries == null) libraries = new StringBuffer();
			else libraries.append(",");
			libraries.append(name);
			libraries.append("/");
			libraries.append(this.move_paths.get(name));
		}

		this.property.setProperty("libraries", libraries.toString());
		this.property.store(new FileOutputStream(this.filename), "net.homeunix.nac.media.Manager");
	}

	/**
	 * ファイルマッチパターンを返す(クローン渡し)
	 * @return ファイルマッチパターン
	 */
	public String getFilenamePattern() {
		return new String(this.filenamepattern);
	}

	/**
	 * 初期化する
	 * @param filter ファイルフィルター
	 */
	private void initialize(FilenamePatternWithLastmodifiedFilter filter) {
		this.filter = filter;
		//JTree_fileを単一選択に変更
		this.getJTree_file().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.getJTree_file().getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		//JComboBox_saveの保存先をセット
		this.getJComboBox_move().setModel(new DefaultComboBoxModel(this.move_names.toArray()));
	}

	/**
	 * Modelを再構成しフォルダーツリーを準備する
	 */
	private void prepareTree() {
		//初期化されていない場合Exceptionを発行する
		if (this.directory == null) throw new NullPointerException("directory not set. Maybe not call loadsProperty() beforee.");
		if (this.filter == null) throw new NullPointerException("filter not set. Maybe not call initialize() beforee.");

		//todateのデフォルト値を判定
		int fromindex = this.getJComboBox_fromday().getSelectedIndex();
		int toindex = this.getJComboBox_today().getSelectedIndex();
		if (fromindex < toindex) toindex = 0;

		//todateデータモデル用コレクションを作成
		List<String> todate = new ArrayList<String>();
		for (int i=0; i<=fromindex; i++) todate.add(this.ALLDAYS.get(i));

		//todateデータモデルを登録
		jComboBox_today.setModel(new DefaultComboBoxModel(todate.toArray()));

		//todateのデフォルト値をセット
		this.listener = false;
		this.getJComboBox_today().setSelectedIndex(toindex);
		this.listener = true;

		//最終更新日を取り込み
		this.filter.setUpdateAfter(constant.day.get(this.getJComboBox_fromday().getSelectedItem()));
		this.filter.setUpdateBefore(constant.day.get(this.getJComboBox_today().getSelectedItem()));
		//移動/検収ボタンをdisable
		this.jButton_move.setEnabled(false);
		this.jButton_accept.setEnabled(false);
		//TreeModelの再構成し登録
		this.getJTree_file().setModel(new DefaultTreeModel(this.composeTreeNodeByFile(new File(this.directory))));
	}

	/**
	 * ファイル関連付け実行
	 * @param filename ファイルパス
	 */
	private void executeByAssociation(String filename) {
		File file = new File(filename);
		//ディレクトリーなら何もしない
		if (file.isDirectory()) return;

		//ファイルの場合は実行
		try {
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ファイル移動
	 * @param filename ファイルパス
	 */
	 private boolean moveFiles(String filename, String source_path, String destination_path) {
		String relative = "";

		//予め一階層下にセット
		File src = new File(filename).getParentFile();

		//パスが一致するまで遡りつつ相対パスを記録する
		while (!source_path.equalsIgnoreCase(src.getPath())) {
			if (src.isDirectory()) relative = src.getName() + this.fs_delimiter + relative;
			src = src.getParentFile();
		}
		src = new File(filename);

		//移動先ディレクトリを作成
		File dst = new File(destination_path + this.fs_delimiter + relative);
		dst.mkdirs();

		//移動
		try {
//			System.out.println(src.getName() + dst);
			src.renameTo(new File(dst, src.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		//最後のファイルの場合上位ディレクトリを再帰的に削除
		src = new File(filename).getParentFile();
		while (src.listFiles().length == 0) {
			src.delete();
			src = src.getParentFile();
		}
		return true;
	}

	/** Fileからディレクトリー構造のTreeNodeを構成する
	 * private DefaultMutableTreeNode composeTreeNodeByFile(File base)
	 * @param base TreeNodeを作成するFileオブジェクト
	 * @return Fileに対応するTreeNode
	 */
	private DefaultMutableTreeNode composeTreeNodeByFile(File base) {
		//Fileに対応するTreeNodeを作成
		DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(base.getName());
		//配下のFile collectionを作成
		List<File> files = java.util.Arrays.asList(base.listFiles());
		//File collectionを走査
		for (File file : files) {
			//Filterに一致したものをTreeNodeとして登録する。ディレクトリの場合は再帰呼び出し
			if (this.filter.accept(file, file.getName()))
				treenode.add(file.isDirectory() ? composeTreeNodeByFile(file) : new DefaultMutableTreeNode(file.getName()));
		}
		return treenode;
	}

	/**
	 * 子ノードをすべて削除する
	 * @param node 削除するべき親ノード
	 */
	@SuppressWarnings("unchecked")
	private void deleteChildNode(DefaultTreeModel model, DefaultMutableTreeNode node) {

		java.util.Enumeration<DefaultMutableTreeNode> e = node.children();
		for (DefaultMutableTreeNode child : Collections.list(e) ) {
			if (!child.isLeaf()) this.deleteChildNode(model, child);
			model.removeNodeFromParent(child);
		}
	}

	/**
	 * TreePathオブジェクトからStringオブジェクトにファイルパス変換
	 * @param treepathオブジェクト
	 * @return Stringオブジェクト
	 */
	private String convertTreepathToPath(TreePath treepath) {
		//nullで初期化
		StringBuffer fullpath = null;

		//pathからnodeに分離
		List<Object> nodes = java.util.Arrays.asList(treepath.getPath());
		for (Object node : nodes) {
			//初回の場合directoryを代入
			if (fullpath == null) {
				fullpath = new StringBuffer(this.directory);
				continue;
			}
			//二回目以降は文字列をappend
			fullpath.append("\\");
			fullpath.append(node.toString());
		}
		return fullpath.toString();
	}

	/**
	 * 検収ボタン押下時アクション
	 * @param event
	 */
	private void jButton_acceptActionActionPerformed(ActionEvent event) {
		File file;
		String mastername;
		String additional;
		List<TreePath> paths;

		if (this.getJTree_file().getSelectionPath() != null) {
			//初期パスをセット
			paths = new LinkedList<TreePath>(java.util.Arrays.asList(this.getJTree_file().getSelectionPaths()));

			//繰り返し処理
			for (TreePath path : this.getJTree_file().getSelectionPaths()) {
				mastername = null;

				//Pathからファイル名に変換
				String filename = this.convertTreepathToPath(path);
				//ファイル作成
				file = new File(filename);

				//master_directory内で一致するファイル名を特定する
				additional = "";
				for (File child : new File(master_directory).listFiles()) {
					switch (file.getName().indexOf(child.getName())) {
					case 0:
						mastername = child.getPath();
						break;
					default:
						mastername = child.getPath();
						additional = "\\" + file.getName().replace(child.getName(), "");
					case -1:
						break;
					}
				}

				if (mastername == null) {
					System.out.println("該当ファイルなし");
					continue;
				}

				//移動の場合
				if (this.accept_action.equalsIgnoreCase(ACCEPT_ACTION.MOVE.name())) {
					if (this.moveFiles(mastername, this.master_directory, new String(this.accepted_directory).concat(additional)))
						paths.remove(path);
				}

				//削除の場合
				if (this.accept_action.equalsIgnoreCase(ACCEPT_ACTION.DELETE.name())) {
					new File(master_directory, mastername).delete();
					paths.remove(path);
				}
			}
			this.jTree_file.setSelectionPaths(paths.toArray(new TreePath[0]));
		}
	}

	/**
	 * ファイル選択時アクション
	 * @param event
	 */
	private void jTree_fileTreeSelectionValueChanged(TreeSelectionEvent event) {
		boolean directory = false;
		String filepath;
		String otherpath;
		List<TreePath> paths;
		TreePath other = null;

		//まず移動/検収不可に設定
		this.jButton_move.setEnabled(false);
		this.jButton_accept.setEnabled(false);

		JTree tree = (JTree) event.getSource();

		//何も選択されていないnullの場合は無視する
		if (tree.getSelectionPath() != null) {
			//初期パスをセット
			paths = new LinkedList<TreePath>(java.util.Arrays.asList(tree.getSelectionPaths()));
			for (TreePath path : tree.getSelectionPaths()) {
				//rootは選択させない
				if (((DefaultMutableTreeNode)path.getLastPathComponent()).isRoot()) {
					paths.remove(path);
					continue;
				}

				//Pathからファイル名に変換
				filepath = this.convertTreepathToPath(path);

				//ディレクトリーのサブノードは選択解除する
				if (new File(filepath).isDirectory()) {
					directory = true;
					for (Iterator<TreePath> i = paths.iterator(); i.hasNext();) {
						other = i.next();
						//自身の場合は処理しない
						if (path != other) {
							otherpath = this.convertTreepathToPath(other);
							//一致すれば選択解除する
							if (otherpath.startsWith(filepath)) i.remove();
						}
					}
				}
			}
			//変更があれば選択設定する
			if (tree.getSelectionCount() != paths.size()) {
				this.jTree_file.setSelectionPaths(paths.toArray(new TreePath[0]));
				return;
			}
			//選択件数が0でなければ移動/検収を使用可能にする
			if (paths.size() != 0) {
				this.jButton_move.setEnabled(true);
				if (!directory) this.jButton_accept.setEnabled(true);
			}
		}
	}

	/**
	 * ファイルダブルクリック時アクション
	 * @param event
	 */
	private void jTree_fileMouseMouseClicked(MouseEvent event) {
		//クリックの場合
		if (SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
			//ツリーからパスを作成
			JTree tree = (JTree) event.getSource();
			String filename = this.convertTreepathToPath(tree.getPathForLocation(event.getX(), event.getY()));
			//実行
			this.executeByAssociation(filename);
		}
	}

	/**
	 * 始点対象日変更時アクション
	 * @param event
	 */
	private void jComboBox_fromdayItemItemStateChanged(ItemEvent event) {
		//jComboBox_fromdayが更新されたらTreeの準備/更新を実施
		if (this.listener && (event.getStateChange() == ItemEvent.SELECTED)) this.prepareTree();
	}

	/**
	 * ファイル上でキー入力時アクション
	 * @param event
	 */
	private void jTree_fileKeyKeyPressed(KeyEvent event) {
		//エンターキーの場合
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			//ツリーからパスを作成
			JTree tree = (JTree) event.getSource();
			String filename = this.convertTreepathToPath(tree.getLeadSelectionPath());
			//実行
			this.executeByAssociation(filename);
		}
	}

	/**
	 * 移動ボタン押下時アクション
	 * @param event
	 */
	private void jButton_moveActionActionPerformed(ActionEvent event) {
		DefaultMutableTreeNode node;

		//何も選択されていないnullの場合は無視する
		if (this.getJTree_file().getSelectionPath() != null) {
			//ツリーからパスを作成
			for (TreePath path : this.getJTree_file().getSelectionPaths()) {
				String filename = this.convertTreepathToPath(path);
				//移動
				this.moveFiles(filename, this.directory, this.move_paths.get(this.getJComboBox_move().getSelectedItem()));

				//ツリーからノードを削除
				DefaultTreeModel model = (DefaultTreeModel)this.getJTree_file().getModel();
				node = (DefaultMutableTreeNode)path.getLastPathComponent();
				this.deleteChildNode(model, node);
				model.removeNodeFromParent(node);
			}
			this.getJTree_file().clearSelection();
		}
	}

	/**
	 * 終点対象日変更時アクション
	 * @param event
	 */
	private void jComboBox_todayItemItemStateChanged(ItemEvent event) {
		//jComboBox_todayが更新されたらTreeの準備/更新を実施
		if (this.listener && (event.getStateChange() == ItemEvent.SELECTED)) this.prepareTree();
	}

	private void jButton_expandActionActionPerformed(ActionEvent event) {
		int row = 0;
		while(row<this.jTree_file.getRowCount()) {
			this.jTree_file.expandRow(row);
			row++;
		}
	}
}
