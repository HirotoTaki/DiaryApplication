/* これは起動時に実行される。 */

-- 3つのテーブルが作られていなければ作成する。
-- 連続して作成された数字idをプライマリキーとする。
-- 日記のデータを格納
CREATE TABLE IF NOT EXISTS diary(
	-- シリアル
	id serial,
	-- 分類
	category varchar(2),
	-- タイトル
	title varchar(50) NOT NULL,
	-- 文章
	content text,
	-- 日付
	date date NOT NULL,
	-- 投稿日
	update_datetime timestamp,
	PRIMARY KEY(id)
);

-- 検索条件の分類を管理
CREATE TABLE IF NOT EXISTS category_code(
	id serial,
	-- 投稿者の所属グループ？
	group_cd varchar(2),
	-- 分類
	cd varchar(2),
	-- 投稿者の名前？
	name varchar(20),
	PRIMARY KEY(id)
);

-- ログインユーザを管理
CREATE TABLE IF NOT EXISTS users(
	id serial,
	user_id varchar(10) NOT NULL,
	password varchar(60) NOT NULL,
	username varchar(50),
	PRIMARY KEY(id)
);