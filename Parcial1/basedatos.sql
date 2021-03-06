PGDMP     (    &                w            prueba     10.7 (Ubuntu 10.7-1.pgdg16.04+1)    11.2     V           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            W           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            X           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            Y           1262    24617    prueba    DATABASE     x   CREATE DATABASE prueba WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE prueba;
             postgres    false            Z           0    0    DATABASE prueba    ACL     (   GRANT ALL ON DATABASE prueba TO magela;
                  postgres    false    2905            �            1259    24633 
   permission    TABLE     ]   CREATE TABLE public.permission (
    id integer NOT NULL,
    functionality character(20)
);
    DROP TABLE public.permission;
       public         magela    false            �            1259    24631    permission_id_seq    SEQUENCE     �   CREATE SEQUENCE public.permission_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.permission_id_seq;
       public       magela    false    200            [           0    0    permission_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;
            public       magela    false    199            �            1259    24620    rol    TABLE     \   CREATE TABLE public.rol (
    idrol integer NOT NULL,
    namerol character(20) NOT NULL
);
    DROP TABLE public.rol;
       public         magela    false            �            1259    24618    rol_idrol_seq    SEQUENCE     �   CREATE SEQUENCE public.rol_idrol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.rol_idrol_seq;
       public       magela    false    197            \           0    0    rol_idrol_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.rol_idrol_seq OWNED BY public.rol.idrol;
            public       magela    false    196            �            1259    24644    rolpermission    TABLE     e   CREATE TABLE public.rolpermission (
    idrol integer NOT NULL,
    idpermission integer NOT NULL
);
 !   DROP TABLE public.rolpermission;
       public         magela    false            �            1259    24626    userapp    TABLE     �   CREATE TABLE public.userapp (
    ci integer NOT NULL,
    name character(20),
    surname character(20),
    username character(10) NOT NULL,
    password character(10) DEFAULT 1234 NOT NULL,
    idroluser integer NOT NULL
);
    DROP TABLE public.userapp;
       public         magela    false            �
           2604    24636    permission id    DEFAULT     n   ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);
 <   ALTER TABLE public.permission ALTER COLUMN id DROP DEFAULT;
       public       magela    false    200    199    200            �
           2604    24623 	   rol idrol    DEFAULT     f   ALTER TABLE ONLY public.rol ALTER COLUMN idrol SET DEFAULT nextval('public.rol_idrol_seq'::regclass);
 8   ALTER TABLE public.rol ALTER COLUMN idrol DROP DEFAULT;
       public       magela    false    196    197    197            R          0    24633 
   permission 
   TABLE DATA               7   COPY public.permission (id, functionality) FROM stdin;
    public       magela    false    200   I       O          0    24620    rol 
   TABLE DATA               -   COPY public.rol (idrol, namerol) FROM stdin;
    public       magela    false    197   �       S          0    24644    rolpermission 
   TABLE DATA               <   COPY public.rolpermission (idrol, idpermission) FROM stdin;
    public       magela    false    201          P          0    24626    userapp 
   TABLE DATA               S   COPY public.userapp (ci, name, surname, username, password, idroluser) FROM stdin;
    public       magela    false    198   Y       ]           0    0    permission_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permission_id_seq', 1, false);
            public       magela    false    199            ^           0    0    rol_idrol_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.rol_idrol_seq', 1, false);
            public       magela    false    196            �
           2606    24638    permission permission_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public         magela    false    200            �
           2606    24625    rol rol_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (idrol);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public         magela    false    197            �
           2606    24648     rolpermission rolpermission_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.rolpermission
    ADD CONSTRAINT rolpermission_pkey PRIMARY KEY (idrol, idpermission);
 J   ALTER TABLE ONLY public.rolpermission DROP CONSTRAINT rolpermission_pkey;
       public         magela    false    201    201            �
           2606    24630    userapp user_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.userapp
    ADD CONSTRAINT user_pkey PRIMARY KEY (ci);
 ;   ALTER TABLE ONLY public.userapp DROP CONSTRAINT user_pkey;
       public         magela    false    198            �
           2606    24654    rolpermission idpermission    FK CONSTRAINT     �   ALTER TABLE ONLY public.rolpermission
    ADD CONSTRAINT idpermission FOREIGN KEY (idpermission) REFERENCES public.permission(id);
 D   ALTER TABLE ONLY public.rolpermission DROP CONSTRAINT idpermission;
       public       magela    false    200    201    2767            �
           2606    24649    rolpermission idrol    FK CONSTRAINT     q   ALTER TABLE ONLY public.rolpermission
    ADD CONSTRAINT idrol FOREIGN KEY (idrol) REFERENCES public.rol(idrol);
 =   ALTER TABLE ONLY public.rolpermission DROP CONSTRAINT idrol;
       public       magela    false    201    197    2763            �
           2606    24662    userapp idroluser    FK CONSTRAINT     s   ALTER TABLE ONLY public.userapp
    ADD CONSTRAINT idroluser FOREIGN KEY (idroluser) REFERENCES public.rol(idrol);
 ;   ALTER TABLE ONLY public.userapp DROP CONSTRAINT idroluser;
       public       magela    false    198    2763    197            R   \   x�]�K
� E�q������j:IKAT�v�['����Va��
���p�9:��9�Wxd��\Au�qo�J��ǄD��kF�H�焈/��(o      O   U   x�3�LL����,.)JL�/R� .#��ļ�Լ���Լ�|��1gqiAjQYf1\)H؄3(�X�Y���$lʙ^�Z\��
�b���� ���      S   /   x��� 0��pL%�k���`�l��j�7��-���w$}�IQ      P   �   x�m���0g�)�H�; ����₩��Tr[�*�F��醓lk����Y��h`?��E��%5��Y`�s?�h�ڱx�eJ���c��D�$��%�����3�" GGi����C";��� T$i������}�.Z�Y^kq�|��!M���O�Y�e��o��hz     