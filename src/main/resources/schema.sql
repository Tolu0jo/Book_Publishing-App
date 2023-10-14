DROP TABLE IF EXISTS "books"
DROP TABLE IF EXISTS "authors"

CREATE TABLE "authors"(
  "id" bigint DEFAULT nextval("authors_id_seq") NOT NULL,
  "name" text,
  "age" integer,
  CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books"(
        "isbn" text NOT NULL,
        "name" text,
        "author_id" bigint,
         CONSTRAINT "books_pkey" PRIMARY KEY ("id"),
         CONSTRAINT "fk_author" PRIMARY KEY ("author_id")
         REFERENCES authors(id)

);